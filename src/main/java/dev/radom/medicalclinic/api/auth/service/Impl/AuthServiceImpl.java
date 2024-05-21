package dev.radom.medicalclinic.api.auth.service.Impl;

import dev.radom.medicalclinic.api.auth.dto.*;
import dev.radom.medicalclinic.api.auth.mapper.AuthMapper;
import dev.radom.medicalclinic.api.auth.repository.AuthRepository;
import dev.radom.medicalclinic.api.auth.service.AuthService;
import dev.radom.medicalclinic.api.mail.Mail;
import dev.radom.medicalclinic.api.mail.MailService;
import dev.radom.medicalclinic.api.user.model.User;
import dev.radom.medicalclinic.api.user.service.UserService;
import dev.radom.medicalclinic.api.user.web.NewUserDto;
import dev.radom.medicalclinic.utils.RandomCode;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${spring.mail.username}")
    private String adminMail;
    private final MailService mailService;

    private final AuthRepository authRepository;
    private final UserService userService;
    private final AuthMapper authMapper;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtEncoder jwtEncoder;
    private JwtEncoder jwtRefreshTokenEncoder;


    @Autowired
    void setJwtRefreshTokenEncoder(@Qualifier("refreshTokenJwtEncoder") JwtEncoder jwtRefreshTokenEncoder) {
        this.jwtRefreshTokenEncoder = jwtRefreshTokenEncoder;
    }

    @Override
    public AuthDto refreshToken(RefreshTokenDto refreshTokenDto) {
        return null;
    }

    @Transactional
    @Override
    public void register(RegisterDto registerDto) {

        NewUserDto newUserDto = authMapper.mapRegisterDtoToNewUserDto(registerDto);


        String verifiedCode = RandomCode.generateCode();
        //Store verifiedCode in database
        userService.createNewUser(newUserDto, verifiedCode);

        //Send verifiedCode via email
        Mail<String> verifiedMail = new Mail<>();
        verifiedMail.setSubject("Email Verification");

        verifiedMail.setSender(adminMail);
        verifiedMail.setReceiver(newUserDto.email());
        verifiedMail.setTemplate("auth/verify-mail");
        verifiedMail.setMetaData(verifiedCode);
        try {
            mailService.sendMail(verifiedMail);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthDto login(LoginDto loginDto) {

        Authentication auth = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        auth = daoAuthenticationProvider.authenticate(auth);

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));


        return AuthDto.builder()
                .type("Bearer")
                .accessToken(
                        generateAccessToken(
                                GenerateTokenDto.builder()
                                        .auth(auth.getName())
                                        .scope(scope)
                                        .expiration(Instant.now().plus(1, ChronoUnit.HOURS))
                                        .build()))
                .refreshToken(
                        generateRefreshToken(
                                GenerateTokenDto.builder()
                                        .auth(auth.getName())
                                        .scope(scope)
                                        .expiration(Instant.now().plus(24, ChronoUnit.HOURS))
                                        .build()))
                .build();
    }

    private String generateAccessToken(GenerateTokenDto generateTokenDto) {
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(generateTokenDto.auth())
                .issuer("Public")
                .issuedAt(now)
                .expiresAt(generateTokenDto.expiration())
                .subject("Access Token")
                .audience(List.of("Public Client"))
                .claim("scope", generateTokenDto.scope())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    private String generateRefreshToken(GenerateTokenDto generateTokenDto) {
        Instant now = Instant.now();
        JwtClaimsSet jwtRefreshTokenClaimsSet = JwtClaimsSet.builder()
                .id(generateTokenDto.auth())
                .issuer("Public")
                .issuedAt(now)
                .expiresAt(generateTokenDto.expiration())
                .subject("Refresh Token")
                .audience(List.of("Refresh Client"))
                .claim("scope", generateTokenDto.scope())
                .build();
        return jwtRefreshTokenEncoder.encode(JwtEncoderParameters.from(jwtRefreshTokenClaimsSet)).getTokenValue();
    }

    @Override
    public void verify(VerifyDto verifyDto) {
        User verifiedUser = authRepository.findByEmailAndVerifiedCodeAndIsDeletedFalse(verifyDto.email(), verifyDto.verifiedCode())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Verify email has been failed..!"
                ));
        verifiedUser.setIsVerified(true);
        verifiedUser.setVerifiedCode(null);
        authRepository.save(verifiedUser);
    }
}

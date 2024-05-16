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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${spring.mail.username}")
    private String adminMail;
    private final UserService userService;
    private final AuthRepository authRepository;
    //    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final AuthMapper authMapper;

    //    private final RandomCode randomCode;
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
        return null;
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

package dev.radom.medicalclinic.api.auth.web;

import dev.radom.medicalclinic.api.auth.dto.*;
import dev.radom.medicalclinic.api.auth.service.AuthService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @Value("${app.base-uri}")
    private String appBaseUri;

    @PostMapping("/token")
    public PayloadApi<?> refreshToken(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {

        AuthDto authDto = authService.refreshToken(refreshTokenDto);
        boolean success = true;
        int code = HttpStatus.OK.value();
        String message = "Token refreshed successfully";

        return PayloadApi.builder()
                .success(success)
                .code(code)
                .message(message)
                .error(null)
                .payload(authDto)
                .build();
    }

    @PostMapping("/register")
    public PayloadApi<?> register(@Valid @RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        boolean success = true;
        int code = HttpStatus.CREATED.value();
        String message = "Registration successful. Please check your email.";
        return PayloadApi.builder()
                .success(success)
                .code(code)
                .message(message)
                .error(null)
                .build();
    }

    @PostMapping("/verify")
    public PayloadApi<?> verifiedCode(@RequestBody VerifyDto verifyDto) {

        authService.verify(verifyDto);
        boolean success = true;
        int code = HttpStatus.OK.value();
        String message = "Email verification successful!";
        return PayloadApi.builder()
                .success(success)
                .code(code)
                .message(message)
                .error(null)
                .build();
    }

    @PostMapping("/login")
    public PayloadApi<?> login(@Valid @RequestBody LoginDto loginDto) {
        AuthDto authDto = authService.login(loginDto);
        boolean success = true;
        int code = HttpStatus.OK.value();
        String message = "Login successful!";
        return PayloadApi.builder()
                .success(success)
                .code(code)
                .message(message)
                .error(null)
                .payload(authDto)
                .build();
    }
}

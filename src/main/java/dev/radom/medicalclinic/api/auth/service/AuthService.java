package dev.radom.medicalclinic.api.auth.service;


import dev.radom.medicalclinic.api.auth.dto.*;

public interface AuthService {
    AuthDto refreshToken(RefreshTokenDto refreshTokenDto);

    void register(RegisterDto registerDto);

    AuthDto login(LoginDto loginDto);

    void verify(VerifyDto verifyDto);
}

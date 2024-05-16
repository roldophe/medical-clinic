package dev.radom.medicalclinic.api.auth.dto;


import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDto(@NotBlank
                              String refreshToken) {
}
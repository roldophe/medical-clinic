package dev.radom.medicalclinic.api.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record VerifyDto(@NotBlank
                        String email,
                        @NotBlank
                        String verifiedCode) {
}

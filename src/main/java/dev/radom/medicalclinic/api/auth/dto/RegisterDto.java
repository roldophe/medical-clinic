package dev.radom.medicalclinic.api.auth.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;
@Builder
public record RegisterDto(@NotBlank
                          String username,
                          @NotBlank
                          @Email
                          String email,
                          @NotBlank
                          String password,
                          @NotNull
                          Set<UUID> roleIds) {
}
package dev.radom.medicalclinic.api.auth.dto;


import jakarta.validation.constraints.*;

import java.util.Set;
import java.util.UUID;

public record RegisterDto(@NotBlank
                          String username,
                          @NotBlank
                          @Email
                          String email,
                          @NotBlank
                          String password,
                          @NotBlank
                          @Size(min = 3)
                          String nickName,
                          @NotNull
                          Set<UUID> roleIds) {
}
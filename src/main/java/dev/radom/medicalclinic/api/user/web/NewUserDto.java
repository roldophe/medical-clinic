package dev.radom.medicalclinic.api.user.web;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record NewUserDto(@NotBlank
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

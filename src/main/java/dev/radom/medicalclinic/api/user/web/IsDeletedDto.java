package dev.radom.medicalclinic.api.user.web;

import jakarta.validation.constraints.NotBlank;

public record IsDeletedDto(@NotBlank
                           Boolean isDeleted) {
}

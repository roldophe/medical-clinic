package dev.radom.medicalclinic.api.auth.dto;


import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank
                       String username,
                       @NotBlank
                       String password) {

}
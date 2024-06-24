package dev.radom.medicalclinic.api.doctor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DoctorRegistrationDTO(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "License number is required") @Pattern(regexp = "^[A-Za-z0-9]{10}$", message = "License number must be 10 alphanumeric characters")
        String licenseNumber,
        @NotBlank(message = "Primary specialty is required")
        String primarySpecialty,
        String secondarySpecialty,
        @NotBlank(message = "Gender is required") @Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
        String gender,
        @NotNull(message = "Birth date is required")
        LocalDate birthDate,
        @NotBlank(message = "Address is required")
        String address,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
        String phoneNumber
) {
}

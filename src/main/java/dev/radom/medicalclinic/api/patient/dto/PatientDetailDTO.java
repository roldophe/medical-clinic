package dev.radom.medicalclinic.api.patient.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatientDetailDTO(
        UUID patientId,
        String firstName,
        String lastName,
        Character gender,
        LocalDate birthDate,
        String address,
        String email,
        String phoneNumber,
        String maritalStatus,
        UUID createdBy,
        LocalDateTime createdAt,
        UUID updatedBy,
        LocalDateTime updatedAt
) {
}

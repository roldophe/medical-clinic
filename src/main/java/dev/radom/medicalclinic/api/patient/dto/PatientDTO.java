package dev.radom.medicalclinic.api.patient.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientDTO(
        UUID patientId,
        String firstName,
        String lastName,
        Character gender,
        String address,
        String email,
        String phoneNumber,
        String maritalStatus
) {
}

package dev.radom.medicalclinic.api.patient.dto;

import java.util.UUID;

public record PatientSnippetDto(UUID patientId,
                                String firstName,
                                String lastName,
                                Character gender,
                                String phoneNumber) {
}

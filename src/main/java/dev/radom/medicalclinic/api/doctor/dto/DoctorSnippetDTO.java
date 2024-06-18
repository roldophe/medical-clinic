package dev.radom.medicalclinic.api.doctor.dto;

import java.util.UUID;

public record DoctorSnippetDTO(UUID doctorId,
                               String firstName,
                               String lastName,
                               String email,
                               String phoneNumber) {
}

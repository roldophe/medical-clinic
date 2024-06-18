package dev.radom.medicalclinic.api.doctor.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DoctorDetailDTO(UUID doctorId,
                              String firstName,
                              String lastName,
                              String licenseNumber,
                              String primarySpecialty,
                              String secondarySpecialty,
                              Character gender,
                              LocalDate birthDate,
                              String address,
                              String email,
                              String phoneNumber,
                              UUID createdBy,
                              LocalDateTime createdAt,
                              UUID updatedBy,
                              LocalDateTime updatedAt) {
}

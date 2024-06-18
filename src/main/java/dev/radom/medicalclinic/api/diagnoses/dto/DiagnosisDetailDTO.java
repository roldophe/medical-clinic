package dev.radom.medicalclinic.api.diagnoses.dto;

import dev.radom.medicalclinic.api.patient.dto.PatientSnippetDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DiagnosisDetailDTO(UUID diagnosisId,
                                 PatientSnippetDto patient,
                                 String diagnosisName,
                                 String diagnosisDescription,
                                 LocalDate diagnosisDate,
                                 String severity,
                                 UUID createdBy,
                                 LocalDateTime createdAt,
                                 UUID updatedBy,
                                 LocalDateTime updatedAt) {
}

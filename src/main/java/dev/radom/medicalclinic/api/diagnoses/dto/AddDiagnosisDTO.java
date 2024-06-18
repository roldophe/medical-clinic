package dev.radom.medicalclinic.api.diagnoses.dto;


import java.time.LocalDate;
import java.util.UUID;

public record AddDiagnosisDTO(UUID patientId,
                              String diagnosisName,
                              String diagnosisDescription,
                              LocalDate diagnosisDate,
                              String severity) {
}

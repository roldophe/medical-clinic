package dev.radom.medicalclinic.api.diagnoses.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateDiagnosisDTO(UUID patientId,
                                 String diagnosisName,
                                 String diagnosisDescription,
                                 String severity) {
}

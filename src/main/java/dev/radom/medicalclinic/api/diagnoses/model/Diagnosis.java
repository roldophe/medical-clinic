package dev.radom.medicalclinic.api.diagnoses.model;

import dev.radom.medicalclinic.api.patient.model.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "diagnoses")
public class Diagnosis {
    @Id
    @GeneratedValue
    private UUID diagnosisId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String diagnosisName;
    private String diagnosisDescription;
    private LocalDate diagnosisDate;
    private String severity;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public Diagnosis(UUID diagnosisId, Patient patient, String diagnosisName, String diagnosisDescription, LocalDate diagnosisDate, String severity, UUID createdBy, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
        this.diagnosisId = diagnosisId;
        this.patient = patient;
        this.diagnosisName = diagnosisName;
        this.diagnosisDescription = diagnosisDescription;
        this.diagnosisDate = diagnosisDate;
        this.severity = severity;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}

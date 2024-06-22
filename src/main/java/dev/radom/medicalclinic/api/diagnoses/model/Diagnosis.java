package dev.radom.medicalclinic.api.diagnoses.model;

import dev.radom.medicalclinic.api.patient.model.Patient;
import dev.radom.medicalclinic.base.BaseEntity;
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
public class Diagnosis extends BaseEntity {
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
}

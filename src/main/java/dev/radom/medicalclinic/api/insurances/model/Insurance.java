package dev.radom.medicalclinic.api.insurances.model;

import dev.radom.medicalclinic.api.patient.model.Patient;
import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "insurances")
public class Insurance extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID insuranceId;

    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private String policyHolderName;
    private String policyNumber;
    private String policyHolderEmail;
    private Double sumInsured;
    private Double medicalExpense;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}

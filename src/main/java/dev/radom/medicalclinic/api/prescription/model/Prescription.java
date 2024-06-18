package dev.radom.medicalclinic.api.prescription.model;

import dev.radom.medicalclinic.api.appointment.model.Appointment;
import dev.radom.medicalclinic.api.medicine.model.Medicine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "prescriptions")
public class Prescription {
     @Id
    @GeneratedValue
    private UUID prescriptionId;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private LocalDateTime date;
    private Integer dosage;
    private String prescriptionNotes;
    private Boolean refillsAllowed;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;


}

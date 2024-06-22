package dev.radom.medicalclinic.api.appointment.model;

import dev.radom.medicalclinic.api.doctor.model.Doctor;
import dev.radom.medicalclinic.api.patient.model.Patient;
import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
     @Id
    @GeneratedValue
    private UUID appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean inProgress;
    private String reasonForAppointment;
    private Boolean isFollowUp;
    private String status;
    private String additionalInfo;
    private Boolean isNewPatient;
}

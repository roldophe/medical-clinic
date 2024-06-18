package dev.radom.medicalclinic.api.appointment.model;

import dev.radom.medicalclinic.api.doctor.model.Doctor;
import dev.radom.medicalclinic.api.patient.model.Patient;
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
public class Appointment {
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
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public Appointment(LocalDateTime updatedAt, UUID updatedBy, LocalDateTime createdAt, UUID createdBy, Boolean isNewPatient, String additionalInfo, String status, Boolean isFollowUp, String reasonForAppointment, Boolean inProgress, LocalDateTime endTime, LocalDateTime startTime, Doctor doctor, Patient patient, UUID appointmentId) {
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.isNewPatient = isNewPatient;
        this.additionalInfo = additionalInfo;
        this.status = status;
        this.isFollowUp = isFollowUp;
        this.reasonForAppointment = reasonForAppointment;
        this.inProgress = inProgress;
        this.endTime = endTime;
        this.startTime = startTime;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentId = appointmentId;
    }
}

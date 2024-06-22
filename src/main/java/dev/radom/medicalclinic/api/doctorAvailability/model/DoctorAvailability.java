package dev.radom.medicalclinic.api.doctorAvailability.model;

import dev.radom.medicalclinic.api.doctor.model.Doctor;
import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability extends BaseEntity {
   @Id
    @GeneratedValue
    private UUID doctorAvailabilityId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

}

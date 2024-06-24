package dev.radom.medicalclinic.api.patient.model;

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
@Table(name = "patients")
public class Patient extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID patientId;

    private String firstName;
    private String lastName;
    private Character gender;
    private LocalDate birthDate;
    private String address;
    private String email;
    private String phoneNumber;
    private String maritalStatus;
}

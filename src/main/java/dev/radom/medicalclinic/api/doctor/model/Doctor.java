package dev.radom.medicalclinic.api.doctor.model;

import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "doctors")
public class Doctor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID doctorId;

    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String primarySpecialty;
    private String secondarySpecialty;
    private Character gender;
    private LocalDate birthDate;
    private String address;
    private String email;
    private String phoneNumber;

}

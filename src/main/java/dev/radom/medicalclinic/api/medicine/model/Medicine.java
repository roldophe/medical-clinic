package dev.radom.medicalclinic.api.medicine.model;

import dev.radom.medicalclinic.api.prescription.model.Prescription;
import dev.radom.medicalclinic.base.BaseEntity;
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
@Table(name = "medicines")
public class Medicine extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID medicineId;

    private String medicineName;
    private Integer quantity;
    private String category;
    private String dosageForm;
    private Boolean isGeneric;
}

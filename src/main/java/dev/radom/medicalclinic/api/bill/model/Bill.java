package dev.radom.medicalclinic.api.bill.model;

import dev.radom.medicalclinic.api.appointment.model.Appointment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue
    private UUID billId;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private LocalDateTime billDate;
    private String billItems;
    private Double totalAmount;
    private String billStatus;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;
}

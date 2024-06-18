package dev.radom.medicalclinic.api.paymentMethod.model;

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
@Table(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue
    private UUID paymentMethodId;

    private String methodName;
    private String description;
    private Boolean isActive = true;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;
}

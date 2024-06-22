package dev.radom.medicalclinic.api.paymentMethod.model;

import dev.radom.medicalclinic.base.BaseEntity;
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
public class PaymentMethod extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID paymentMethodId;

    private String methodName;
    private String description;
    private Boolean isActive = true;
}

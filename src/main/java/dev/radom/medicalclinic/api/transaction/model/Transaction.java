package dev.radom.medicalclinic.api.transaction.model;

import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID transactionId;

    private LocalDateTime transactionDate;
    private Double amount;
    private String description;
}

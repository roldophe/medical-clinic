package dev.radom.medicalclinic.api.pay.model;

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
@Table(name = "pays")
public class Pay extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID payId;

    private LocalDateTime payDate;
    private BigDecimal paidAmount;
}

package dev.radom.medicalclinic.api.payDetail.model;

import dev.radom.medicalclinic.api.bill.model.Bill;
import dev.radom.medicalclinic.api.pay.model.Pay;
import dev.radom.medicalclinic.api.paymentMethod.model.PaymentMethod;
import dev.radom.medicalclinic.api.transaction.model.Transaction;
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
@Table(name = "pay_details")
public class PayDetail extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID payDetailId;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "pay_id")
    private Pay pay;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    private String paymentStatus;
}

package dev.radom.medicalclinic.api.pay.dto;

import java.math.BigDecimal;

public record UpdatePayDTO(BigDecimal paidAmount) {
}

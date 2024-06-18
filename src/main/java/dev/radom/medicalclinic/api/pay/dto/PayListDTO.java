package dev.radom.medicalclinic.api.pay.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PayListDTO(UUID payId, LocalDateTime payDate, BigDecimal paidAmount) {
}

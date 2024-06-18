package dev.radom.medicalclinic.api.pay.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PayDTO(UUID payId, LocalDateTime payDate, BigDecimal paidAmount, UUID createdBy, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
}

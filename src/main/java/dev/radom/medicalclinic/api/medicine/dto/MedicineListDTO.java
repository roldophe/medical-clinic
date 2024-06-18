package dev.radom.medicalclinic.api.medicine.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicineListDTO(UUID medicineId, String medicineName, Integer quantity, String category, String dosageForm, Boolean isGeneric, UUID createdBy, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
}

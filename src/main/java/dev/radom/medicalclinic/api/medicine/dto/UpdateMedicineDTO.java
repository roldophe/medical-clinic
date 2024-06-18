package dev.radom.medicalclinic.api.medicine.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateMedicineDTO(String medicineName, Integer quantity, String category, String dosageForm, Boolean isGeneric) {
}

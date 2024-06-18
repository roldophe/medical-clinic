package dev.radom.medicalclinic.api.medicine.dto;

public record AddMedicineDTO(String medicineName,
                             Integer quantity,
                             String category,
                             String dosageForm,
                             Boolean isGeneric) {
}

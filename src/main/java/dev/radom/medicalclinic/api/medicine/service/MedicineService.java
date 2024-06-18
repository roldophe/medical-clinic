package dev.radom.medicalclinic.api.medicine.service;

import dev.radom.medicalclinic.api.medicine.dto.AddMedicineDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineDetailDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineListDTO;
import dev.radom.medicalclinic.api.medicine.dto.UpdateMedicineDTO;

import java.util.List;
import java.util.UUID;

public interface MedicineService {
     void save(AddMedicineDTO addMedicineDTO);
    MedicineDetailDTO findById(UUID medicineId);
    List<MedicineListDTO> findAllMedicines();
    void deleteById(UUID medicineId);
    void update(UUID medicineId, UpdateMedicineDTO updateMedicineDTO);
}

package dev.radom.medicalclinic.api.medicine.service.impl;

import dev.radom.medicalclinic.api.medicine.dto.AddMedicineDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineDetailDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineListDTO;
import dev.radom.medicalclinic.api.medicine.dto.UpdateMedicineDTO;
import dev.radom.medicalclinic.api.medicine.mapper.MedicineMapper;
import dev.radom.medicalclinic.api.medicine.model.Medicine;
import dev.radom.medicalclinic.api.medicine.repository.MedicineRepository;
import dev.radom.medicalclinic.api.medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    @Override
    public void save(AddMedicineDTO addMedicineDTO) {
        Medicine medicine = medicineMapper.mapAddMedicineDTOToMedicine(addMedicineDTO);
        medicineRepository.save(medicine);
    }

    @Override
    public MedicineDetailDTO findById(UUID medicineId) {
        return medicineRepository.findById(medicineId)
                .map(medicineMapper::mapMedicineToMedicineDetailDTO)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found"));
    }

    @Override
    public List<MedicineListDTO> findAllMedicines() {
        return medicineMapper.mapMedicineListToMedicineListDTO(medicineRepository.findAll());
    }

    @Override
    public void deleteById(UUID medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found"));
        medicineRepository.delete(medicine);
    }

    @Override
    public void update(UUID medicineId, UpdateMedicineDTO updateMedicineDTO) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found"));

        medicineMapper.mapUpdateMedicineDTOToMedicine(updateMedicineDTO, medicine);
        medicineRepository.save(medicine);
    }
}

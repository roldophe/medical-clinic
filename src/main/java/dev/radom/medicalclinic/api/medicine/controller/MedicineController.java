package dev.radom.medicalclinic.api.medicine.controller;

import dev.radom.medicalclinic.api.doctor.dto.UpdateDoctorDTO;
import dev.radom.medicalclinic.api.medicine.dto.AddMedicineDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineDetailDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineListDTO;
import dev.radom.medicalclinic.api.medicine.dto.UpdateMedicineDTO;
import dev.radom.medicalclinic.api.medicine.service.MedicineService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping
    public PayloadApi<List<MedicineListDTO>> getMedicines() {
        List<MedicineListDTO> medicines = medicineService.findAllMedicines();
        return new PayloadApi<>(true, 200, "Successfully retrieved all medicines", null, medicines);
    }

    @PostMapping
    public void addMedicine(@RequestBody AddMedicineDTO medicineDTO) {
        medicineService.save(medicineDTO);
    }

    @GetMapping("/{medicineId}")
    public PayloadApi<MedicineDetailDTO> findMedicineById(@PathVariable UUID medicineId) {
        MedicineDetailDTO medicineDetail = medicineService.findById(medicineId);
        return new PayloadApi<>(true, 200, "Successfully retrieved medicine details", null, medicineDetail);
    }

    @PutMapping("/{medicineId}")
    public void updateDoctor(@PathVariable UUID medicineId, @RequestBody UpdateMedicineDTO updateMedicineDTO) {
        medicineService.update(medicineId, updateMedicineDTO);
    }
}

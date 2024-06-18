package dev.radom.medicalclinic.api.medicine.mapper;

import dev.radom.medicalclinic.api.medicine.dto.AddMedicineDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineDetailDTO;
import dev.radom.medicalclinic.api.medicine.dto.MedicineListDTO;
import dev.radom.medicalclinic.api.medicine.dto.UpdateMedicineDTO;
import dev.radom.medicalclinic.api.medicine.model.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    Medicine mapAddMedicineDTOToMedicine(AddMedicineDTO addMedicineDTO);
    void mapUpdateMedicineDTOToMedicine(UpdateMedicineDTO updateMedicineDTO,@MappingTarget Medicine medicine);
    List<MedicineListDTO> mapMedicineListToMedicineListDTO(List<Medicine> medicines);
    MedicineDetailDTO mapMedicineToMedicineDetailDTO(Medicine medicine);
}

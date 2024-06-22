package dev.radom.medicalclinic.api.doctorAvailability.mapper;

import dev.radom.medicalclinic.api.doctorAvailability.dto.AddNewDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityDetailDTO;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityListDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.UpdateDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.model.DoctorAvailability;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorAvailabilityMapper {
    List<DoctorAvailabilityListDto> mapDoctorAvailabilityListDtoList(List<DoctorAvailability> doctorAvailabilityList);
    DoctorAvailability mapAddNewDoctorAvailabilityDtoDoctorAvailability(AddNewDoctorAvailabilityDto addNewDoctorAvailabilityDto);
    void updateDoctorAvailability(@MappingTarget DoctorAvailability doctorAvailability, UpdateDoctorAvailabilityDto updateDoctorAvailabilityDto);
    DoctorAvailabilityDetailDTO mapDoctorAvailabilityToDoctorAvailabilityDetailDTO(DoctorAvailability doctorAvailability);
}

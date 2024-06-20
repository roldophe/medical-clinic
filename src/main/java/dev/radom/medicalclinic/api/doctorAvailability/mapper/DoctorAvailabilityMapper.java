package dev.radom.medicalclinic.api.doctorAvailability.mapper;

import dev.radom.medicalclinic.api.doctorAvailability.dto.AddNewDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityDetailDTO;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityListDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.UpdateDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.model.DoctorAvailability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorAvailabilityMapper {
    List<DoctorAvailabilityListDto> mapDoctorAvailabilityListDtoList(List<DoctorAvailability> doctorAvailabilityList);
    @Mapping( target = "doctorId",source = "doctor.doctorId")
    DoctorAvailabilityListDto mapDoctorAvailabilityListDto(DoctorAvailability doctorAvailability);
    @Mapping( target = "doctor.doctorId",source = "doctorId")
    DoctorAvailability mapAddNewDoctorAvailabilityDtoDoctorAvailability(AddNewDoctorAvailabilityDto addNewDoctorAvailabilityDto);
    void updateDoctorAvailability(@MappingTarget DoctorAvailability doctorAvailability, UpdateDoctorAvailabilityDto updateDoctorAvailabilityDto);
    DoctorAvailabilityDetailDTO mapDoctorAvailabilityToDoctorAvailabilityDetailDTO(DoctorAvailability doctorAvailability);
}

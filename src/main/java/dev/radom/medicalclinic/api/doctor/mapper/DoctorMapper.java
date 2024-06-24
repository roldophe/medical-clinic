package dev.radom.medicalclinic.api.doctor.mapper;

import dev.radom.medicalclinic.api.doctor.dto.*;
import dev.radom.medicalclinic.api.doctor.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDetailDTO mapDoctorToDetailDTO(Doctor doctor);
    List<DoctorListDTO> mapDoctorsToListDTO(List<Doctor> doctors);
    Doctor mapNewDoctorToAddNewDoctorDTO(AddNewDoctorDTO addNewDoctorDTO);
    void updateDoctorWithUpdateDTO(@MappingTarget Doctor doctor, UpdateDoctorDTO updateDoctorDTO);
    Doctor mapDoctorRegistrationDtoToDoctor(DoctorRegistrationDTO doctorRegistrationDTO);
}

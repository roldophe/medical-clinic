package dev.radom.medicalclinic.api.patient.mapper;

import dev.radom.medicalclinic.api.patient.dto.AddNewPatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDetailDTO;
import dev.radom.medicalclinic.api.patient.dto.UpdatePatientDTO;
import dev.radom.medicalclinic.api.patient.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "patientId",source = "patient.patientId")
    PatientDTO toPatientDTO(Patient patient);
    PatientDetailDTO toPatientDetailDTO(Patient patient);
    List<PatientDTO> toPatientDTO(List<Patient> patients);

    Patient toPatient(AddNewPatientDTO addNewPatientDTO);
    void updatePatient(@MappingTarget Patient patient, UpdatePatientDTO updatePatientDTO);
}

package dev.radom.medicalclinic.api.diagnoses.mapper;

import dev.radom.medicalclinic.api.diagnoses.dto.AddDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDetailDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.UpdateDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.model.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {

    @Mapping(target = "patient", source = "diagnosis.patient")
    DiagnosisDetailDTO toDiagnosisDetailDTO(Diagnosis diagnosis);
    Diagnosis toDiagnosis(AddDiagnosisDTO addDiagnosisDTO);
    List<DiagnosisDTO> toDiagnosesDTO(List<Diagnosis> diagnoses);
    @Mapping(target = "patientId",source = "patient.patientId")
    DiagnosisDTO toDiagnosisDTO(Diagnosis Diagnosis);
    void updateDiagnosis(@MappingTarget Diagnosis diagnosis, UpdateDiagnosisDTO updateDiagnosisDTO);
}

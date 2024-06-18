package dev.radom.medicalclinic.api.insurances.mapper;

import dev.radom.medicalclinic.api.insurances.dto.AddNewInsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDetailDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.UpdateInsuranceDto;
import dev.radom.medicalclinic.api.insurances.model.Insurance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    Insurance mapAddNewInsuranceDto(AddNewInsuranceDto addNewInsuranceDto);

    void updateInsuranceWithInsuranceDto(@MappingTarget Insurance insurance, UpdateInsuranceDto updateInsuranceDto);
    List<InsuranceDto> mapInsuranceListDto(List<Insurance> insuranceList);
    @Mapping(target = "patientId", source = "patient.patientId")
    InsuranceDto mapInsuranceListDto(Insurance insurance);
    @Mapping(target = "patient", source = "insurance.patient")
    InsuranceDetailDto mapInsuranceDetailDto(Insurance insurance);
}

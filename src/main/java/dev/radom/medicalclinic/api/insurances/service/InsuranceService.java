package dev.radom.medicalclinic.api.insurances.service;

import dev.radom.medicalclinic.api.insurances.dto.AddNewInsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDetailDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.UpdateInsuranceDto;

import java.util.List;
import java.util.UUID;

public interface InsuranceService {
    void save(AddNewInsuranceDto addNewInsuranceDto);
    List<InsuranceDto> getInsuranceList();
    void update(UUID patientId, UpdateInsuranceDto updateInsuranceDto);
    void deleteByPatientId(UUID patientId);
    InsuranceDetailDto findByPatientId(UUID patientId);
    InsuranceDetailDto findById(UUID insuranceId);
}

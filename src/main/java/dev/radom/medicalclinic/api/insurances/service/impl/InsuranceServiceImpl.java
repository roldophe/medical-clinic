package dev.radom.medicalclinic.api.insurances.service.impl;
import dev.radom.medicalclinic.api.insurances.dto.AddNewInsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDetailDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.UpdateInsuranceDto;
import dev.radom.medicalclinic.api.insurances.mapper.InsuranceMapper;
import dev.radom.medicalclinic.api.insurances.model.Insurance;
import dev.radom.medicalclinic.api.insurances.repository.InsuranceRepository;
import dev.radom.medicalclinic.api.insurances.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceMapper insuranceMapper;

    @Override
    public void save(AddNewInsuranceDto addNewInsuranceDto) {
        Insurance insurance = insuranceMapper.mapAddNewInsuranceDto(addNewInsuranceDto);
        insuranceRepository.save(insurance);
    }

    @Override
    public List<InsuranceDto> getInsuranceList() {
        return insuranceMapper.mapInsuranceListDto(insuranceRepository.findAll());
    }

    @Override
    public void update(UUID patientId, UpdateInsuranceDto updateInsuranceDto) {
        Insurance insurance = insuranceRepository.findInsuranceByPatientPatientId(patientId).orElseThrow(() -> new RuntimeException("Insurance not found"));
        // Update insurance details based on updateInsuranceDto
        insuranceRepository.save(insurance);
    }

    @Override
    public void deleteByPatientId(UUID patientId) {
        Insurance insurance = insuranceRepository.findInsuranceByPatientPatientId(patientId).orElseThrow(() -> new RuntimeException("Insurance not found"));
        insuranceRepository.delete(insurance);
    }

    @Override
    public InsuranceDetailDto findByPatientId(UUID patientId) {
        Insurance insurance = insuranceRepository.findInsuranceByPatientPatientId(patientId).orElseThrow(() -> new RuntimeException("Insurance not found"));
        return insuranceMapper.mapInsuranceDetailDto(insurance);
    }

    @Override
    public InsuranceDetailDto findById(UUID insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId).orElseThrow(() -> new RuntimeException("Insurance not found"));
        return insuranceMapper.mapInsuranceDetailDto(insurance);
    }
}

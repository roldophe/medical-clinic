package dev.radom.medicalclinic.api.insurances.controller;

import dev.radom.medicalclinic.api.insurances.dto.AddNewInsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDetailDto;
import dev.radom.medicalclinic.api.insurances.dto.InsuranceDto;
import dev.radom.medicalclinic.api.insurances.dto.UpdateInsuranceDto;
import dev.radom.medicalclinic.api.insurances.service.InsuranceService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/insurances")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping
    public PayloadApi<List<InsuranceDto>> listInsurances() {
        List<InsuranceDto> insurances = insuranceService.getInsuranceList();
        return new PayloadApi<>(true, 200, "Successfully retrieved all insurances.", null, insurances);
    }

    @PostMapping
    public void addNewInsurance(@RequestBody AddNewInsuranceDto addNewInsuranceDto) {
        insuranceService.save(addNewInsuranceDto);
    }

    @GetMapping("/patients/{patientId}")
    public PayloadApi<InsuranceDetailDto> getByPatientId(@PathVariable UUID patientId) {
        InsuranceDetailDto insuranceDetail = insuranceService.findByPatientId(patientId);
        return new PayloadApi<>(true, 200, "Successfully retrieved insurance details", null, insuranceDetail);
    }

    @GetMapping("/{insuranceId}")
    public PayloadApi<InsuranceDetailDto> getByInsuranceId(@PathVariable UUID insuranceId) {
        InsuranceDetailDto insuranceDetail = insuranceService.findById(insuranceId);
        return new PayloadApi<>(true, 200, "Successfully retrieved insurance details", null, insuranceDetail);
    }

    @PutMapping("/{patientId}")
    public void updateInsuranceByPatientId(@PathVariable UUID patientId, @RequestBody UpdateInsuranceDto updateInsuranceDto) {
        insuranceService.update(patientId, updateInsuranceDto);
    }

    @DeleteMapping("/{patientId}")
    public void deleteByPatientId(@PathVariable UUID patientId) {
        insuranceService.deleteByPatientId(patientId);
    }
}

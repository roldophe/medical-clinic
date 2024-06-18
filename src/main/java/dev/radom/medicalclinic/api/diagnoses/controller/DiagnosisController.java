package dev.radom.medicalclinic.api.diagnoses.controller;

import dev.radom.medicalclinic.api.diagnoses.dto.AddDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDetailDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.UpdateDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.service.DiagnosisService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/diagnoses")
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @GetMapping()
    public PayloadApi<List<DiagnosisDTO>> getAllDiagnoses() {
        List<DiagnosisDTO> diagnoses = diagnosisService.findAll();
        return new PayloadApi<>(true, 200, "Successfully retrieved all diagnoses.", null, diagnoses);
    }

    @GetMapping("/patient/{patientId}")
    public PayloadApi<List<DiagnosisDTO>> getAllDiagnosisByPatientId(@PathVariable("patientId") UUID patientId) {
        List<DiagnosisDTO> diagnoses = diagnosisService.findAllByPatientId(patientId);
        return new PayloadApi<>(true, 200, "Successfully retrieved all diagnoses by patient.", null, diagnoses);
    }

    @GetMapping("/{diagnosisId}")
    public PayloadApi<DiagnosisDetailDTO> getDiagnosisById(@PathVariable("diagnosisId") UUID diagnosisId) {
        DiagnosisDetailDTO diagnosis = diagnosisService.findById(diagnosisId);
        return new PayloadApi<>(true,200, "Successfully retrieved diagnosis details",null,diagnosis);
    }

    @PutMapping("/{diagnosisId}")
    public void updateDiagnosis(@PathVariable("diagnosisId") UUID diagnosisId, @RequestBody UpdateDiagnosisDTO updateDiagnosisDTO) {
        diagnosisService.update(diagnosisId, updateDiagnosisDTO);
    }

    @PostMapping()
    public void addDiagnosis(@RequestBody AddDiagnosisDTO addDiagnosisDTO) {
        diagnosisService.save(addDiagnosisDTO);
    }
}

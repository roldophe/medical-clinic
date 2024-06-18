package dev.radom.medicalclinic.api.diagnoses.service;

import dev.radom.medicalclinic.api.diagnoses.dto.AddDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDetailDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.UpdateDiagnosisDTO;

import java.util.List;
import java.util.UUID;

public interface DiagnosisService {
    List<DiagnosisDTO> findAll();
    List<DiagnosisDTO> findAllByPatientId(UUID patientId);
    DiagnosisDetailDTO findById(UUID diagnosisId);
    void save(AddDiagnosisDTO addDiagnosisDTO);
    void update(UUID diagnosisId, UpdateDiagnosisDTO updateDiagnosisDTO);
}

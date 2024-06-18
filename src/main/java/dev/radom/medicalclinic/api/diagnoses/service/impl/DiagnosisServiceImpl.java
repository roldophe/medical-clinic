package dev.radom.medicalclinic.api.diagnoses.service.impl;

import dev.radom.medicalclinic.api.diagnoses.dto.AddDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.DiagnosisDetailDTO;
import dev.radom.medicalclinic.api.diagnoses.dto.UpdateDiagnosisDTO;
import dev.radom.medicalclinic.api.diagnoses.mapper.DiagnosisMapper;
import dev.radom.medicalclinic.api.diagnoses.model.Diagnosis;
import dev.radom.medicalclinic.api.diagnoses.repository.DiagnosisRepository;
import dev.radom.medicalclinic.api.diagnoses.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public List<DiagnosisDTO> findAll() {
        return diagnosisMapper.toDiagnosesDTO(diagnosisRepository.findAll());
    }

    @Override
    public List<DiagnosisDTO> findAllByPatientId(UUID patientId) {
        List<Diagnosis> diagnoses = diagnosisRepository.findAllByPatientPatientId(patientId);
        return diagnosisMapper.toDiagnosesDTO(diagnoses);
    }

    @Override
    public DiagnosisDetailDTO findById(UUID diagnosisId) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(()-> new RuntimeException("Diagnosis not found!"));
        return diagnosisMapper.toDiagnosisDetailDTO(diagnosis);
    }

    @Override
    public void save(AddDiagnosisDTO addDiagnosisDTO) {
        Diagnosis diagnosis = diagnosisMapper.toDiagnosis(addDiagnosisDTO);
        diagnosisRepository.save(diagnosis);
    }

    @Override
    public void update(UUID diagnosisId, UpdateDiagnosisDTO updateDiagnosisDTO) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(()-> new RuntimeException("Diagnosis not found!"));
        diagnosisMapper.updateDiagnosis(diagnosis, updateDiagnosisDTO);
        diagnosisRepository.save(diagnosis);
    }
}

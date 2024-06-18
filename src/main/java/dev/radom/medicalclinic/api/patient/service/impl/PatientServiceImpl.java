package dev.radom.medicalclinic.api.patient.service.impl;

import dev.radom.medicalclinic.api.patient.dto.AddNewPatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDetailDTO;
import dev.radom.medicalclinic.api.patient.dto.UpdatePatientDTO;
import dev.radom.medicalclinic.api.patient.mapper.PatientMapper;
import dev.radom.medicalclinic.api.patient.model.Patient;
import dev.radom.medicalclinic.api.patient.repository.PatientRepository;
import dev.radom.medicalclinic.api.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public void createPatient(AddNewPatientDTO addNewPatientDTO) {
        Patient patient = patientMapper.toPatient(addNewPatientDTO);
        patientRepository.save(patient);
    }

    @Override
    public void updatePatient(UUID patientId, UpdatePatientDTO updatePatientDTO) {
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patientMapper.updatePatient(existingPatient, updatePatientDTO);
        patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(UUID patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(patientId);
    }

    @Override
    public PatientDetailDTO findPatientById(UUID patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toPatientDetailDTO(patient);
    }

    @Override
    public List<PatientDTO> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::toPatientDTO)
                .collect(Collectors.toList());
    }
}

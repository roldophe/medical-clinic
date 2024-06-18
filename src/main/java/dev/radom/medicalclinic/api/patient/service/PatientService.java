package dev.radom.medicalclinic.api.patient.service;

import dev.radom.medicalclinic.api.patient.dto.AddNewPatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDetailDTO;
import dev.radom.medicalclinic.api.patient.dto.UpdatePatientDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    void createPatient(AddNewPatientDTO addNewPatientDTO);
    void updatePatient(UUID patientId, UpdatePatientDTO updatePatientDTO);
    void deletePatient(UUID patientId);
    PatientDetailDTO findPatientById(UUID patientId);
    List<PatientDTO> findAllPatients();
}

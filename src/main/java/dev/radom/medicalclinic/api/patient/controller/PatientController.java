package dev.radom.medicalclinic.api.patient.controller;

import dev.radom.medicalclinic.api.patient.dto.AddNewPatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientDetailDTO;
import dev.radom.medicalclinic.api.patient.dto.UpdatePatientDTO;
import dev.radom.medicalclinic.api.patient.service.PatientService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public PayloadApi<List<PatientDTO>> findAll() {
        List<PatientDTO> patients = patientService.findAllPatients();
        return new PayloadApi<>(true, 200, "Successfully retrieved all patients.", null, patients);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PayloadApi<PatientDetailDTO>> findPatientById(@PathVariable UUID patientId) {
        PatientDetailDTO patient = patientService.findPatientById(patientId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved patient.", null, patient));
    }

    @PostMapping
    public ResponseEntity<PayloadApi<Void>> createPatient(@RequestBody AddNewPatientDTO addNewPatientDTO) {
        patientService.createPatient(addNewPatientDTO);
        return ResponseEntity.status(201).body(new PayloadApi<>(true, 201, "Successfully created patient.", null, null));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PayloadApi<Void>> updatePatient(@PathVariable UUID patientId, @RequestBody UpdatePatientDTO updatePatientDTO) {
        patientService.updatePatient(patientId, updatePatientDTO);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully updated patient.", null, null));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<PayloadApi<Void>> deletePatient(@PathVariable UUID patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully deleted patient.", null, null));
    }
}

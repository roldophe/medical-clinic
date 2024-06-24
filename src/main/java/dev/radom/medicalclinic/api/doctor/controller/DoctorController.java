package dev.radom.medicalclinic.api.doctor.controller;

import dev.radom.medicalclinic.api.doctor.dto.*;
import dev.radom.medicalclinic.api.doctor.service.DoctorService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
@Slf4j
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping()
    public PayloadApi<List<DoctorListDTO>> listDoctors() {
        List<DoctorListDTO> doctors = doctorService.findAll();
        return new PayloadApi<>(true, 200, "Successfully retrieved all doctors", null, doctors);
    }

    @PostMapping
    public void addDoctor(@RequestBody AddNewDoctorDTO addNewDoctorDTO) {
        doctorService.save(addNewDoctorDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody @Valid DoctorRegistrationDTO doctorRegistrationDTO) {
        doctorService.registerDoctor(doctorRegistrationDTO);
        return ResponseEntity.ok("Doctor registered successfully.");
    }

    @GetMapping("/{doctorId}")
    public PayloadApi<DoctorDetailDTO> findDoctorById(@PathVariable UUID doctorId) {
        DoctorDetailDTO doctor = doctorService.findById(doctorId);
        return new PayloadApi<>(true, 200, "Successfully retrieved doctor details", null, doctor);
    }

    @PutMapping("/{doctorId}")
    public void updateDoctor(@PathVariable UUID doctorId, @RequestBody UpdateDoctorDTO updateDoctorDTO) {
        doctorService.update(doctorId, updateDoctorDTO);
    }
}

package dev.radom.medicalclinic.api.doctorAvailability.controller;

import dev.radom.medicalclinic.api.doctorAvailability.dto.AddNewDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityDetailDTO;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityListDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.UpdateDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.service.DoctorAvailabilityService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/doctor-available")
@RequiredArgsConstructor
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService doctorAvailabilityService;

    @PostMapping()
    public ResponseEntity<Void> addDoctorAvailability(@RequestBody AddNewDoctorAvailabilityDto addNewDoctorAvailabilityDto) {
        doctorAvailabilityService.save(addNewDoctorAvailabilityDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Void> updateDoctorAvailability(@PathVariable UUID doctorId, @RequestBody UpdateDoctorAvailabilityDto updateDoctorAvailabilityDto) {
        doctorAvailabilityService.update(doctorId, updateDoctorAvailabilityDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<PayloadApi<List<DoctorAvailabilityListDto>>> getAllDoctorAvailability() {
        List<DoctorAvailabilityListDto> doctorAvailabilities = doctorAvailabilityService.findAllDoctorAvailability();
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved all doctorAvailabilities", null, doctorAvailabilities));
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<PayloadApi<DoctorAvailabilityDetailDTO>> getDoctorAvailability(@PathVariable UUID doctorId) {
        DoctorAvailabilityDetailDTO doctorAvailability = doctorAvailabilityService.findDoctorAvailabilityDetailById(doctorId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved a doctorAvailability", null, doctorAvailability));
    }
}

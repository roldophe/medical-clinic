package dev.radom.medicalclinic.api.appointment.controller;

import dev.radom.medicalclinic.api.appointment.dto.AddNewAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDetailDTO;
import dev.radom.medicalclinic.api.appointment.dto.UpdateAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.service.AppointmentService;
import dev.radom.medicalclinic.pagination.PayloadApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public PayloadApi<List<AppointmentDTO>> findAll() {
        List<AppointmentDTO> appointments = appointmentService.getAppointments();
        return new PayloadApi<>(true, 200, "Successfully retrieved all appointments.", null, appointments);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<PayloadApi<AppointmentDetailDTO>> getAppointment(@PathVariable UUID appointmentId) {
        AppointmentDetailDTO appointment = appointmentService.getAppointment(appointmentId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved appointment.", null, appointment));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<PayloadApi<List<AppointmentDTO>>> getAppointmentsByPatientId(@PathVariable UUID patientId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved appointments for patient.", null, appointments));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<PayloadApi<List<AppointmentDTO>>> getAppointmentsByDoctorId(@PathVariable UUID doctorId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved appointments for doctor.", null, appointments));
    }

    @GetMapping("/patient/{patientId}/doctor/{doctorId}")
    public ResponseEntity<PayloadApi<List<AppointmentDTO>>> getAppointmentsByPatientAndDoctorId(@PathVariable UUID patientId, @PathVariable UUID doctorId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByPatientAndDoctorId(patientId, doctorId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully retrieved appointments for patient and doctor.", null, appointments));
    }

    @PostMapping
    public ResponseEntity<PayloadApi<Void>> createAppointment(@RequestBody AddNewAppointmentDTO addNewAppointmentDTO) {
        appointmentService.createAppointment(addNewAppointmentDTO);
        return ResponseEntity.status(201).body(new PayloadApi<>(true, 201, "Successfully created appointment.", null, null));
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<PayloadApi<Void>> updateAppointment(@PathVariable UUID appointmentId, @RequestBody UpdateAppointmentDTO updateAppointmentDTO) {
        appointmentService.updateAppointment(appointmentId, updateAppointmentDTO);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully updated appointment.", null, null));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<PayloadApi<Void>> deleteAppointment(@PathVariable UUID appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok(new PayloadApi<>(true, 200, "Successfully deleted appointment.", null, null));
    }
}

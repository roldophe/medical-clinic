package dev.radom.medicalclinic.api.appointment.service.impl;

import dev.radom.medicalclinic.api.appointment.dto.AddNewAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDetailDTO;
import dev.radom.medicalclinic.api.appointment.dto.UpdateAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.mapper.AppointmentMapper;
import dev.radom.medicalclinic.api.appointment.model.Appointment;
import dev.radom.medicalclinic.api.appointment.repository.AppointmentRepository;
import dev.radom.medicalclinic.api.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public void createAppointment(AddNewAppointmentDTO addNewAppointmentDTO) {
        Appointment appointment = appointmentMapper.mapAddNewAppointmentDTO(addNewAppointmentDTO);
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(UUID appointmentId, UpdateAppointmentDTO updateAppointmentDTO) {
        Appointment existingAppointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointmentMapper.mapUpdateAppointmentDTO(existingAppointment, updateAppointmentDTO);

        appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new RuntimeException("Appointment not found");
        }
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public AppointmentDetailDTO getAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.mapAppointmentToAppointmentDetailDTO(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
            .map(appointmentMapper::mapAppointmentToAppointmentDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientId(UUID patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientPatientId(patientId);
        return appointments.stream()
            .map(appointmentMapper::mapAppointmentToAppointmentDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctorId(UUID doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorDoctorId(doctorId);
        return appointments.stream()
            .map(appointmentMapper::mapAppointmentToAppointmentDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatientAndDoctorId(UUID patientId, UUID doctorId) {
        List<Appointment> appointments = appointmentRepository.findByPatientPatientIdAndDoctorDoctorId(patientId, doctorId);
        return appointments.stream()
            .map(appointmentMapper::mapAppointmentToAppointmentDTO)
            .collect(Collectors.toList());
    }
}

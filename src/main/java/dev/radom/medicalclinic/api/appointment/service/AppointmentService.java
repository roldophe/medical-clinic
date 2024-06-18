package dev.radom.medicalclinic.api.appointment.service;

import dev.radom.medicalclinic.api.appointment.dto.AddNewAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDetailDTO;
import dev.radom.medicalclinic.api.appointment.dto.UpdateAppointmentDTO;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    void createAppointment(AddNewAppointmentDTO addNewAppointmentDTO);
    void updateAppointment(UUID appointmentId, UpdateAppointmentDTO updateAppointmentDTO);
    void deleteAppointment(UUID appointmentId);
    AppointmentDetailDTO getAppointment(UUID appointmentId);
    List<AppointmentDTO> getAppointments();
    List<AppointmentDTO> getAppointmentsByPatientId(UUID patientId);
    List<AppointmentDTO> getAppointmentsByDoctorId(UUID doctorId);
    List<AppointmentDTO> getAppointmentsByPatientAndDoctorId(UUID patientId, UUID doctorId);
}

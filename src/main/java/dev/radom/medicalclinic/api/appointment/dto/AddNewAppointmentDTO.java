package dev.radom.medicalclinic.api.appointment.dto;

import dev.radom.medicalclinic.api.patient.model.Patient;

import java.time.LocalDateTime;
import java.util.UUID;

public record AddNewAppointmentDTO(
                                UUID doctorId,
                                UUID patientId,
                                Boolean isNewPatient,
                                String status,
                                Boolean isFollowUp,
                                String reasonForAppointment,
                                Boolean inProgress,
                                LocalDateTime endTime,
                                LocalDateTime startTime,
                                String additionalInfo
) {
}

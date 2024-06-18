package dev.radom.medicalclinic.api.appointment.dto;

import dev.radom.medicalclinic.api.doctor.dto.DoctorSnippetDTO;
import dev.radom.medicalclinic.api.patient.dto.PatientSnippetDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentDetailDTO(
                                UUID appointmentId,
                                DoctorSnippetDTO doctor,
                                PatientSnippetDto patient,
                                Boolean isNewPatient,
                                String status,
                                Boolean isFollowUp,
                                String reasonForAppointment,
                                Boolean inProgress,
                                LocalDateTime endTime,
                                LocalDateTime startTime,
                                String additionalInfo,
                                LocalDateTime createdAt,
                                UUID createdBy,
                                LocalDateTime updatedAt,
                                UUID updatedBy
) {
}

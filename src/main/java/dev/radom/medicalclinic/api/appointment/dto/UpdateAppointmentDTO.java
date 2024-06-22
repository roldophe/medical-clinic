package dev.radom.medicalclinic.api.appointment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateAppointmentDTO(
                                UUID doctorId,
                                String status,
                                Boolean isFollowUp,
                                String reasonForAppointment,
                                Boolean inProgress,
                                LocalDateTime endTime,
                                LocalDateTime startTime,
                                String additionalInfo
) {
}

package dev.radom.medicalclinic.api.doctorAvailability.dto;

import java.time.LocalTime;
import java.util.UUID;

public record UpdateDoctorAvailabilityDto(UUID doctorId,
                                          String dayOfWeek,
                                          LocalTime startTime,
                                          LocalTime endTime) {
}

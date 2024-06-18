package dev.radom.medicalclinic.api.doctorAvailability.dto;

import java.time.LocalTime;
import java.util.UUID;

public record DoctorAvailabilityListDto(UUID doctorAvailabilityId,
                                        UUID doctorId,
                                        String dayOfWeek,
                                        LocalTime startTime,
                                        LocalTime endTime) {
}

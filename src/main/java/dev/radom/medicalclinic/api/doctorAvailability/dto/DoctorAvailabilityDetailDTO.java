package dev.radom.medicalclinic.api.doctorAvailability.dto;


import java.time.LocalTime;
import java.util.UUID;

public record DoctorAvailabilityDetailDTO(
                                          UUID doctorAvailabilityId,
                                          UUID doctorId,
                                          String dayOfWeek,
                                          LocalTime startTime,
                                          LocalTime endTime) {
}

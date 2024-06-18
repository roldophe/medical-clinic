package dev.radom.medicalclinic.api.doctor.dto;

import java.util.UUID;

public record DoctorListDTO(UUID doctorId,
                            String firstName,
                            String lastName,
                            String licenseNumber,
                            String primarySpecialty,
                            String secondarySpecialty,
                            Character gender,
                            String address,
                            String email,
                            String phoneNumber) {

}

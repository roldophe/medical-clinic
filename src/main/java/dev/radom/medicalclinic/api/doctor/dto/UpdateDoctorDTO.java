package dev.radom.medicalclinic.api.doctor.dto;

public record UpdateDoctorDTO(
                            String licenseNumber,
                            String primarySpecialty,
                            String secondarySpecialty,
                            Character gender,
                            String address,
                            String email,
                            String phoneNumber) {
}

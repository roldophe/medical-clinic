package dev.radom.medicalclinic.api.patient.dto;

import java.time.LocalDate;

public record AddNewPatientDTO(
        String firstName,
        String lastName, 
        Character gender,
        LocalDate birthDat,
        String address, 
        String email, 
        String phoneNumber, 
        String maritalStatus
) {
}

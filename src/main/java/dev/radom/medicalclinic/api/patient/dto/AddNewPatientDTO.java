package dev.radom.medicalclinic.api.patient.dto;

public record AddNewPatientDTO(
        String firstName,
        String lastName, 
        Character gender, 
        String address, 
        String email, 
        String phoneNumber, 
        String maritalStatus
) {
}

package dev.radom.medicalclinic.api.insurances.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AddNewInsuranceDto(
                                 LocalDate policyStartDate,
                                 LocalDate policyEndDate,
                                 String policyHolderName,
                                 String policyNumber,
                                 String policyHolderEmail,
                                 Double sumInsured,
                                 Double medicalExpense,
                                 UUID patientId) {
}

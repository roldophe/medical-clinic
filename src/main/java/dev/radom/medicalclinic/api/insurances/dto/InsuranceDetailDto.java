package dev.radom.medicalclinic.api.insurances.dto;

import dev.radom.medicalclinic.api.patient.dto.PatientSnippetDto;

import java.time.LocalDate;
import java.util.UUID;

public record InsuranceDetailDto(
                               UUID insuranceId,
                               LocalDate policyStartDate,
                               LocalDate policyEndDate,
                               String policyHolderName,
                               String policyNumber,
                               String policyHolderEmail,
                               Double sumInsured,
                               Double medicalExpense,
                               PatientSnippetDto patient
) {
}

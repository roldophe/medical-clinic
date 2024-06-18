package dev.radom.medicalclinic.api.insurances.repository;

import dev.radom.medicalclinic.api.insurances.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {
    Optional<Insurance> findInsuranceByPatientPatientId(UUID patientId);
}

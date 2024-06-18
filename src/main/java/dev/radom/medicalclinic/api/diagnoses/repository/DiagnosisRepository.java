package dev.radom.medicalclinic.api.diagnoses.repository;

import dev.radom.medicalclinic.api.diagnoses.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, UUID> {
    List<Diagnosis> findAllByPatientPatientId(UUID patientId);
}

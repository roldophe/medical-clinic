package dev.radom.medicalclinic.api.doctorAvailability.repository;

import dev.radom.medicalclinic.api.doctorAvailability.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, UUID> {
}

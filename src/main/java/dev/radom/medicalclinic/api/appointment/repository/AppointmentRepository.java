package dev.radom.medicalclinic.api.appointment.repository;

import dev.radom.medicalclinic.api.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findByPatientPatientId(UUID patientId);

    List<Appointment> findByDoctorDoctorId(UUID doctorId);

    List<Appointment> findByPatientPatientIdAndDoctorDoctorId(UUID patientId, UUID doctorId);
}

package dev.radom.medicalclinic.api.pay.repository;

import dev.radom.medicalclinic.api.pay.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayRepository extends JpaRepository<Pay, UUID> {
}

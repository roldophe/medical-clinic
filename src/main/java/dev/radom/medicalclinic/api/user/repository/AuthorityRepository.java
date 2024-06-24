package dev.radom.medicalclinic.api.user.repository;

import dev.radom.medicalclinic.api.user.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {}
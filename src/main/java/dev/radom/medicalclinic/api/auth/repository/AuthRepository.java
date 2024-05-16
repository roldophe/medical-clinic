package dev.radom.medicalclinic.api.auth.repository;

import dev.radom.medicalclinic.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface AuthRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailAndVerifiedCodeAndIsDeletedFalse(String email, String verifiedCode);

}

package dev.radom.medicalclinic.api.user.repository;

import dev.radom.medicalclinic.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsernameAndIsDeletedFalse(String username);

    boolean existsByEmailAndIsDeletedFalse(String email);

    //Native Query
    @Query(value = "SELECT * FROM users WHERE user_id=?1 AND is_deleted=?2", nativeQuery = true)
    Optional<User> selectUserByUuidAndIsDeleted(UUID uuid, Boolean isDeleted);

    Optional<User> findUserByUserId(UUID userId);

     Optional<User> findByUsernameAndIsDeletedFalseAndIsVerifiedTrue(String username);
}

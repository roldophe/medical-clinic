package dev.radom.medicalclinic.dataInit;

import dev.radom.medicalclinic.api.user.model.Authority;
import dev.radom.medicalclinic.api.user.model.Role;
import dev.radom.medicalclinic.api.user.model.User;
import dev.radom.medicalclinic.api.user.repository.AuthorityRepository;
import dev.radom.medicalclinic.api.user.repository.RoleRepository;
import dev.radom.medicalclinic.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    @Override
    @Transactional
    public void run(String... args) {
        UUID createdBy = UUID.randomUUID();

        User user = new User();
        user.setIsDeleted(false);
        user.setIsVerified(true);
        user.setUsername("admin");
        user.setNickName("tocopherol");
        user.setPassword("$2a$12$uGEL.hBd0jhbsLdkQTUxQ.IZBlAWkBCBmC8/Wddnywk4g.r6NStru");
        user.setEmail("admin@example.com");
        user.setCreatedBy(createdBy);

        Authority userProfile = new Authority();
        userProfile.setAuthorityName("user:profile");
        userProfile.setCreatedBy(createdBy);

        Authority readUser = new Authority();
        readUser.setAuthorityName("user:read");
        readUser.setCreatedBy(createdBy);

        Set<Authority> userAuthorities = getUserAuthorities(createdBy, readUser, userProfile);
        authorityRepository.saveAll(userAuthorities);

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setCreatedBy(createdBy);
        adminRole.setAuthorities(userAuthorities); // Ensure this collection is initialized with the saved authorities
        roleRepository.save(adminRole);

        // Assuming you want to assign the ADMIN role to the user
        user.setRoles(Collections.singleton(adminRole));
        userRepository.save(user);

        Set<Authority> staffAuthorities = Set.of(readUser, userProfile);

        Role staffRole = new Role();
        staffRole.setRoleName("STAFF");
        staffRole.setCreatedBy(createdBy);
        staffRole.setAuthorities(staffAuthorities); // Use the modified method
        roleRepository.save(staffRole);
    }

    private static Set<Authority> getUserAuthorities(UUID createdBy, Authority readUser, Authority userProfile) {
        Authority writeUser = new Authority();
        writeUser.setAuthorityName("user:write");
        writeUser.setCreatedBy(createdBy);

        Authority updateUser = new Authority();
        updateUser.setAuthorityName("user:update");
        updateUser.setCreatedBy(createdBy);

        Authority deleteUser = new Authority();
        deleteUser.setAuthorityName("user:delete");
        deleteUser.setCreatedBy(createdBy);

        Set<Authority> userAuthorities = Set.of(readUser, writeUser, deleteUser, updateUser, userProfile);
        return userAuthorities;
    }

}
package dev.radom.medicalclinic.auditAware;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuditorAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        // Replace this with your actual logic to get the current user
        // For example, from the SecurityContext
        // return Optional.of(UUID.fromString("some-uuid-from-security-context"));

        return Optional.of(UUID.randomUUID()); // For demo purposes only
    }
}


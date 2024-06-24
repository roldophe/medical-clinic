package dev.radom.medicalclinic.config;
import dev.radom.medicalclinic.api.user.service.UserService;
import dev.radom.medicalclinic.api.user.web.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component(value = "AuditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<UUID> {
    private final UserService service;
    @Autowired
    public AuditorAwareImpl(UserService service) {
        this.service = service;
    }
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null ||
                !auth.isAuthenticated() ||
                auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserDto data = service.me(auth);

        if (data != null) {
            return Optional.of(data.userId());
        } else {
            return Optional.empty();
        }
    }

}


package dev.radom.medicalclinic.security;

import dev.radom.medicalclinic.api.user.model.User;
import dev.radom.medicalclinic.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userFound = userRepository.findByUsernameAndIsDeletedFalseAndIsVerifiedTrue(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found with username " + username);
                });

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(userFound);

        return customUserDetails;
    }
}

package dev.radom.medicalclinic.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //TODO: What you want to customize.

        // Configure HTTP request authorization rules
        http.authorizeHttpRequests(auth -> auth

                // Permit all requests matching the pattern "/auth/**"
                .requestMatchers("/auth/**").permitAll()

                // Require either "ADMIN" or "STAFF" authority for requests to "/api/v1/users"
                .requestMatchers(HttpMethod.GET, "/api/v1/users/me").hasAuthority("user:profile")
                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority("user:read")
                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAuthority("SCOPE_user:write")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority("SCOPE_user:delete")
                .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAuthority("SCOPE_user:update")

                // If a request matches none of the above patterns, require authentication
                .anyRequest().authenticated()
        /**
         * The original comment suggests using hasAnyAuthority for table authorities and hasAnyRole for table roles,
         * but the current configuration uses hasAnyAuthority for both cases. Ensure consistency in your security model.
         */
        );

        //TODO: Use Default form login and Policy to Stateful
        //        http.formLogin(Customizer.withDefaults());

        //TODO: Configure HTTP Basic for Client Application. Example : Postman, Insomnia ,...
        http.httpBasic(Customizer.withDefaults());
        http.csrf(token -> token.disable());

        //TODO: Update API Policy to Stateless
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }
}

package dev.radom.medicalclinic.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //CREATE ADMIN
        UserDetails ADMIN = User.withUsername("admin").password(passwordEncoder.encode("12345")).roles("ADMIN").build();

        //CREATE STAFF
        UserDetails STAFF = User.withUsername("staff").password(passwordEncoder.encode("12345")).roles("STAFF").build();

        UserDetails USER = User.withUsername("user").password(passwordEncoder.encode("12345")).roles("USER").build();

        //SAVE USER
        manager.createUser(ADMIN);
        manager.createUser(STAFF);
        manager.createUser(USER);
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //TODO: What you want to customize.

        http.authorizeHttpRequests(auth -> auth

                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("api/v1/users").hasAnyRole("ADMIN", "STAFF")
                .anyRequest()
                .authenticated()
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

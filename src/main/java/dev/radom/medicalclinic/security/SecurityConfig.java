package dev.radom.medicalclinic.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
//import dev.radom.medicalclinic.filterConfig.StaticKeyAuthenticationFilter;
import dev.radom.medicalclinic.utils.KeyUtil;
import dev.radom.medicalclinic.filterConfig.RequestValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final KeyUtil keyUtil;
//    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;

    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtRefreshTokenDecoder());
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.addFilterBefore(new RequestValidationFilter(), UsernamePasswordAuthenticationFilter.class);
        //TODO: What you want to customize.
//        http.addFilterAt(staticKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Add your custom filter after the UsernamePasswordAuthenticationFilter
//        http.addFilterAfter(new CustomFilter(), UsernamePasswordAuthenticationFilter.class);

        // Configure HTTP request authorization rules
        http.authorizeHttpRequests(auth -> auth

                        // Permit all requests matching the pattern "/auth/**"
                        .requestMatchers("/api/v1/auth/**", "/api/v1/files/**", "/file/**", "/auth/**").permitAll()
                        // Permit all requests matching the pattern "/auth/**"
                        .requestMatchers("/", "/v3/api-docs/**", "/swagger-ui/**", "/v2/api-docs/**", "/swagger-resources/**").permitAll()

                        // Require either "ADMIN" or "STAFF" authority for requests to "/api/v1/users"
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/me").hasAuthority("SCOPE_user:profile")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority("SCOPE_user:read")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAuthority("SCOPE_user:write")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAuthority("SCOPE_user:delete")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAuthority("SCOPE_user:update")

                        // Require either "ADMIN" or "STAFF" authority for requests to "/api/v1/doctors"
                        .requestMatchers(HttpMethod.GET, "/api/v1/doctors/**").hasAuthority("SCOPE_user:read")
                        .requestMatchers(HttpMethod.POST, "/api/v1/doctors/**").hasAuthority("SCOPE_user:write")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/doctors/**").hasAuthority("SCOPE_user:delete")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/doctors/**").hasAuthority("SCOPE_user:update")

                        // If a request matches none of the above patterns, require authentication
                        .anyRequest().authenticated()
                /**
                 * The original comment suggests using hasAnyAuthority for table authorities and hasAnyRole for table roles,
                 * but the current configuration uses hasAnyAuthority for both cases. Ensure consistency in your security model.
                 */
        );

        // TODO: Configure JWT | OAuth2 Resource Server.
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(Customizer.withDefaults())
        );
        http.csrf(AbstractHttpConfigurer::disable);//http.csrf(token -> token.disable());

        //TODO: Update API policy to STATELESS
        http.sessionManagement(session ->
                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ));


        return http.build();

    }


    @Bean
    @Primary
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtil.getAccessTokenPublicKey()).build();
    }

    @Bean
    @Primary
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    @Primary
    public JWKSource<SecurityContext> jwkSource() {
        JWK jwk = new RSAKey.Builder(keyUtil.getAccessTokenPublicKey())
                .privateKey(keyUtil.getAccessTokenPrivateKey())
                .keyID(UUID.randomUUID().toString())
                .build();
        var jwkSet = new JWKSet(jwk);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    @Bean(name = "refreshTokenJwtEncoder")
    JwtEncoder refreshTokenJwtEncoder(@Qualifier("refreshTokenJWKSource") JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean("jwtRefreshTokenDecoder")
    JwtDecoder jwtRefreshTokenDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyUtil.getRefreshTokenPublicKey()).build();
    }

    @Bean(name = "refreshTokenJWKSource")
    public JWKSource<SecurityContext> refreshTokenjwkSource() {
        JWK jwk = new RSAKey.Builder(keyUtil.getRefreshTokenPublicKey())
                .privateKey(keyUtil.getRefreshTokenPrivateKey())
                .keyID(UUID.randomUUID().toString())
                .build();
        var jwkSet = new JWKSet(jwk);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }
}

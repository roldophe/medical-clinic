package dev.radom.medicalclinic.api.oauth.model;

import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "oauth_access_token")
public class OAuthAccessToken extends BaseEntity {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "username")
    private String username;

    @Column(name = "scope")
    private String scope;

    @Column(name = "authentication_timestamp")
    private LocalDateTime authenticationTimestamp;

    @Column(name = "refresh_token")
    private UUID refreshToken;

    // Getters and setters
}


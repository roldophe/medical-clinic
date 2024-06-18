package dev.radom.medicalclinic.api.oauth.model;

import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "oauth_refresh_token")
public class OAuthRefreshToken extends BaseEntity {

    @Id
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

}


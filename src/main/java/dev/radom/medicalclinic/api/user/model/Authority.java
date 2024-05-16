package dev.radom.medicalclinic.api.user.model;

import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID authorityId;

    @Column(nullable = false, unique = true)
    private String authorityName;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();

    // getters and setters
}



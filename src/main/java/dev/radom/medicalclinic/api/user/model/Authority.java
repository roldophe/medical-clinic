package dev.radom.medicalclinic.api.user.model;

import dev.radom.medicalclinic.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID authorityId;

    @Column(nullable = false, unique = true)
    private String authorityName;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles = new HashSet<>();

}



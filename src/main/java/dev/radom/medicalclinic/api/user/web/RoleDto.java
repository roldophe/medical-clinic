    package dev.radom.medicalclinic.api.user.web;

    import lombok.Builder;

    import java.util.UUID;
    @Builder
    public record RoleDto(UUID roleId,
                          String roleName) {
    }

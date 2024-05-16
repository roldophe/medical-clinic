package dev.radom.medicalclinic.api.user.web;

import java.util.Set;
import java.util.UUID;

public record UserDto(
        UUID userId,
        String username,
        String email,
        String nickName,
        Set<RoleDto> roles){ // Assuming RoleDto is defined similarly
}

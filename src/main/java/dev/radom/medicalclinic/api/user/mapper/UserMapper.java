package dev.radom.medicalclinic.api.user.mapper;

import dev.radom.medicalclinic.api.user.model.Role;
import dev.radom.medicalclinic.api.user.model.User;
import dev.radom.medicalclinic.api.user.web.NewUserDto;
import dev.radom.medicalclinic.api.user.web.UpdateUserDto;
import dev.radom.medicalclinic.api.user.web.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles")
    UserDto toUserDto(User user);

    void fromUpdateUserDto(@MappingTarget User user, UpdateUserDto updateUserDto);

    @Mapping(target = "roles", source = "roleIds", qualifiedByName = "mapRoleIdsToRoles")
    User toUser(NewUserDto newUserDto);

    @Named("mapRoleIdsToRoles")
    default Set<Role> mapRoleIdsToRoles(Set<UUID> roleIds) {
        return roleIds.stream()
                .map(roleId -> {
                    Role role = new Role();
                    role.setRoleId(roleId);
                    return role;
                })
                .collect(Collectors.toSet());
    }

}

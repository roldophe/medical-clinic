package dev.radom.medicalclinic.api.auth.mapper;

import dev.radom.medicalclinic.api.auth.dto.RegisterDto;
import dev.radom.medicalclinic.api.user.web.NewUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "roleIds", source = "roleIds")
    NewUserDto mapRegisterDtoToNewUserDto(RegisterDto registerDto);
}

package dev.radom.medicalclinic.api.auth.mapper;

import dev.radom.medicalclinic.api.auth.dto.RegisterDto;
import dev.radom.medicalclinic.api.user.web.NewUserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    NewUserDto mapRegisterDtoToNewUserDto(RegisterDto registerDto);
}

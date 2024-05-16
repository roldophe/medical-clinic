package dev.radom.medicalclinic.api.user.service.Impl;

import dev.radom.medicalclinic.api.user.mapper.UserMapper;
import dev.radom.medicalclinic.api.user.model.Role;
import dev.radom.medicalclinic.api.user.model.User;
import dev.radom.medicalclinic.api.user.repository.RoleRepository;
import dev.radom.medicalclinic.api.user.repository.UserRepository;
import dev.radom.medicalclinic.api.user.service.UserService;
import dev.radom.medicalclinic.api.user.web.NewUserDto;
import dev.radom.medicalclinic.api.user.web.UpdateUserDto;
import dev.radom.medicalclinic.api.user.web.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    //    private final UserRoleRepository userRoleRepository;
    //    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto me(Authentication authentication) {
        return null;
    }

    @Override
    @Transactional
    public void createNewUser(NewUserDto newUserDto, String verifiedCode) {
        //Check username if exits
        if (userRepository.existsByUsernameAndIsDeletedFalse(newUserDto.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exits..!");
        }
        //Check email if exist
        if (userRepository.existsByEmailAndIsDeletedFalse(newUserDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exits..!");
        }
        boolean isNotFound = newUserDto.roleIds().stream()
                .anyMatch(roleId -> !roleRepository.existsById(roleId));
        if (isNotFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role ID doesn't exits in db!");
        }

        User user = userMapper.toUser(newUserDto);

        Set<Role> roles = new HashSet<>();
        newUserDto.roleIds().stream().forEach(roleId ->
                roles.add(roleRepository.findById(roleId).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role ID doesn't exits in db!")
                ))
        );
//        user.setPassword(passwordEncoder.encode(newUserDto.password()));
        user.setIsVerified(false);
        user.setVerifiedCode(verifiedCode);
        user.setIsDeleted(false);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void updateByUuid(UUID uuid, UpdateUserDto updateUserDto) {
        //step1: check email of user in the database
        if (userRepository.existsByEmailAndIsDeletedFalse(updateUserDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exits..!");
        }
        //step2: check uuid of user in the database
        User foundUser = userRepository.findUserByUserId(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User UUID = %s doesn't exits in db!", uuid)));

        userMapper.fromUpdateUserDto(foundUser, updateUserDto);
        userRepository.save(foundUser);
    }

    @Override
    public UserDto findByUuid(UUID uuid) {

        User foundUser = userRepository.selectUserByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User UUID = %s doesn't exits in db!", uuid)));
        return userMapper.toUserDto(foundUser);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        User foundUser = userRepository.selectUserByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User UUID = %s doesn't exits in db!", uuid)));
        foundUser.setIsDeleted(true);
        userRepository.save(foundUser);
    }

    @Override
    public void updateIsDeletedByUuid(UUID uuid, Boolean isDeletedDto) {
        User foundUser = userRepository.selectUserByUuidAndIsDeleted(uuid, true)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("User UUID = %s doesn't exits in db!", uuid)));
        foundUser.setIsDeleted(false);
        userRepository.save(foundUser);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

}

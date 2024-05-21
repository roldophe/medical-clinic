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
import dev.radom.medicalclinic.pagination.PageWrapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto me(Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();
        assert jwt != null;
        String username = jwt.getId();
        User isFound = userRepository.findByUsernameAndIsDeletedFalseAndIsVerifiedTrue(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")
        );
        return userMapper.toUserDto(isFound);
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
        user.setPassword(passwordEncoder.encode(newUserDto.password()));
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
    public PageWrapper<UserDto> findAll(Integer page, Integer size) {

        Page<UserDto> users = userRepository.findAll(PageRequest.of(page, size)).map(userMapper::toUserDto);
        return new PageWrapper<>(users);
    }

}

package dev.radom.medicalclinic.api.doctor.service.impl;

import dev.radom.medicalclinic.api.doctor.dto.*;
import dev.radom.medicalclinic.api.doctor.mapper.DoctorMapper;
import dev.radom.medicalclinic.api.doctor.model.Doctor;
import dev.radom.medicalclinic.api.doctor.repository.DoctorRepository;
import dev.radom.medicalclinic.api.doctor.service.DoctorService;
import dev.radom.medicalclinic.api.user.mapper.UserMapper;
import dev.radom.medicalclinic.api.user.model.Role;
import dev.radom.medicalclinic.api.user.model.User;
import dev.radom.medicalclinic.api.user.repository.RoleRepository;
import dev.radom.medicalclinic.api.user.repository.UserRepository;
import dev.radom.medicalclinic.api.user.web.NewUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public void save(AddNewDoctorDTO addNewDoctorDTO) {
        Doctor doctor = doctorMapper.mapNewDoctorToAddNewDoctorDTO(addNewDoctorDTO);
        doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void registerDoctor(DoctorRegistrationDTO doctorRegistrationDTO) {

        Doctor doctor = doctorMapper.mapDoctorRegistrationDtoToDoctor(doctorRegistrationDTO);
        doctorRepository.save(doctor);

        String email = doctorRegistrationDTO.email();
        String username = doctorRegistrationDTO.username();
        String password = doctorRegistrationDTO.password();

        // Check if username exists
        if (userRepository.existsByUsernameAndIsDeletedFalse(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists!");
        }

        // Check if email exists
        if (userRepository.existsByEmailAndIsDeletedFalse(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists!");
        }
        Role doctorRole = roleRepository.findByRoleName("DOCTOR");
        if (doctorRole == null) {
            throw new IllegalStateException("Role DOCTOR not found in the database.");
        }

//        user.setRoles(Set.of(doctorRole));
        User user = userMapper.toUser(NewUserDto.builder()
                .email(email)
                .username(username)
                .password(password)
                        .roleIds(Collections.singleton(doctorRole.getRoleId()))
                .build());


        user.setPassword(passwordEncoder.encode(password));
        user.setIsVerified(true);
        user.setVerifiedCode(null);
        user.setIsDeleted(false);

        userRepository.save(user);
    }

    @Override
    public List<DoctorListDTO> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.mapDoctorsToListDTO(doctors);
    }

    @Override
    public DoctorDetailDTO findById(UUID id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        return doctorMapper.mapDoctorToDetailDTO(doctor);
    }

    @Override
    public void update(UUID id, UpdateDoctorDTO updateDoctorDTO) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        doctorMapper.updateDoctorWithUpdateDTO(doctor, updateDoctorDTO);
        assert doctor != null;
        doctorRepository.save(doctor);
    }
}

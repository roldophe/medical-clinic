package dev.radom.medicalclinic.api.doctor.service;

import dev.radom.medicalclinic.api.doctor.dto.*;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    void save(AddNewDoctorDTO addNewDoctorDTO);
    void registerDoctor(DoctorRegistrationDTO doctorRegistrationDTO);
    List<DoctorListDTO> findAll();
    DoctorDetailDTO findById(UUID id);
    void update(UUID id, UpdateDoctorDTO updateDoctorDTO);
}

package dev.radom.medicalclinic.api.doctor.service;

import dev.radom.medicalclinic.api.doctor.dto.AddNewDoctorDTO;
import dev.radom.medicalclinic.api.doctor.dto.DoctorDetailDTO;
import dev.radom.medicalclinic.api.doctor.dto.DoctorListDTO;
import dev.radom.medicalclinic.api.doctor.dto.UpdateDoctorDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    void save(AddNewDoctorDTO addNewDoctorDTO);
    List<DoctorListDTO> findAll();
    DoctorDetailDTO findById(UUID id);
    void update(UUID id, UpdateDoctorDTO updateDoctorDTO);
}

package dev.radom.medicalclinic.api.doctor.service.impl;

import dev.radom.medicalclinic.api.doctor.dto.AddNewDoctorDTO;
import dev.radom.medicalclinic.api.doctor.dto.DoctorDetailDTO;
import dev.radom.medicalclinic.api.doctor.dto.DoctorListDTO;
import dev.radom.medicalclinic.api.doctor.dto.UpdateDoctorDTO;
import dev.radom.medicalclinic.api.doctor.mapper.DoctorMapper;
import dev.radom.medicalclinic.api.doctor.model.Doctor;
import dev.radom.medicalclinic.api.doctor.repository.DoctorRepository;
import dev.radom.medicalclinic.api.doctor.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private  final DoctorMapper doctorMapper;
    @Override
    public void save(AddNewDoctorDTO addNewDoctorDTO) {
        Doctor doctor = doctorMapper.mapNewDoctorToAddNewDoctorDTO(addNewDoctorDTO);
        doctorRepository.save(doctor);
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

package dev.radom.medicalclinic.api.doctorAvailability.service.impl;

import dev.radom.medicalclinic.api.doctorAvailability.dto.AddNewDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityDetailDTO;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityListDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.UpdateDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.mapper.DoctorAvailabilityMapper;
import dev.radom.medicalclinic.api.doctorAvailability.model.DoctorAvailability;
import dev.radom.medicalclinic.api.doctorAvailability.repository.DoctorAvailabilityRepository;
import dev.radom.medicalclinic.api.doctorAvailability.service.DoctorAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final DoctorAvailabilityMapper doctorAvailabilityMapper;


    @Override
    public void save(AddNewDoctorAvailabilityDto addNewDoctorAvailabilityDto) {
        DoctorAvailability doctorAvailability = doctorAvailabilityMapper.mapAddNewDoctorAvailabilityDtoDoctorAvailability(addNewDoctorAvailabilityDto);
        doctorAvailabilityRepository.save(doctorAvailability);
    }

    @Override
    public void update(UUID doctorAvailabilityId, UpdateDoctorAvailabilityDto updateDoctorAvailabilityDto) {
         DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findById(doctorAvailabilityId).orElseThrow();
         doctorAvailabilityMapper.updateDoctorAvailability(doctorAvailability, updateDoctorAvailabilityDto);
         doctorAvailabilityRepository.save(doctorAvailability);
    }

    @Override
    public List<DoctorAvailabilityListDto> findAllDoctorAvailability() {
        return doctorAvailabilityMapper.mapDoctorAvailabilityListDtoList(doctorAvailabilityRepository.findAll());
    }

    @Override
    public void deleteDoctorAvailabilityById(UUID doctorAvailabilityId) {
        DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findById(doctorAvailabilityId).orElseThrow();
        doctorAvailabilityRepository.delete(doctorAvailability);
    }

    @Override
    public DoctorAvailabilityDetailDTO findDoctorAvailabilityDetailById(UUID doctorAvailabilityId) {
        return doctorAvailabilityMapper.mapDoctorAvailabilityToDoctorAvailabilityDetailDTO(
                doctorAvailabilityRepository.findById(doctorAvailabilityId)
                        .orElseThrow()
        );
    }
}

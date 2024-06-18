package dev.radom.medicalclinic.api.doctorAvailability.service;

import dev.radom.medicalclinic.api.doctorAvailability.dto.AddNewDoctorAvailabilityDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityDetailDTO;
import dev.radom.medicalclinic.api.doctorAvailability.dto.DoctorAvailabilityListDto;
import dev.radom.medicalclinic.api.doctorAvailability.dto.UpdateDoctorAvailabilityDto;

import java.util.List;
import java.util.UUID;

public interface DoctorAvailabilityService {
    void save(AddNewDoctorAvailabilityDto addNewDoctorAvailabilityDto);
    void update(UUID doctorAvailabilityId, UpdateDoctorAvailabilityDto updateDoctorAvailabilityDto);
    List<DoctorAvailabilityListDto> findAllDoctorAvailability();
    void deleteDoctorAvailabilityById(UUID doctorAvailabilityId);
    DoctorAvailabilityDetailDTO findDoctorAvailabilityDetailById(UUID doctorAvailabilityId);
}

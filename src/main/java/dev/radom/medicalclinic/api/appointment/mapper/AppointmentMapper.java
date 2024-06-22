package dev.radom.medicalclinic.api.appointment.mapper;

import dev.radom.medicalclinic.api.appointment.dto.AddNewAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDTO;
import dev.radom.medicalclinic.api.appointment.dto.AppointmentDetailDTO;
import dev.radom.medicalclinic.api.appointment.dto.UpdateAppointmentDTO;
import dev.radom.medicalclinic.api.appointment.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment mapAddNewAppointmentDTO(AddNewAppointmentDTO addNewAppointmentDTO);
    void mapUpdateAppointmentDTO(@MappingTarget Appointment appointment, UpdateAppointmentDTO updateAppointmentDTO);

    @Mapping(target = "patientId", source = "patient.patientId")
    @Mapping(target = "doctorId", source = "doctor.doctorId")
    AppointmentDTO mapAppointmentToAppointmentDTO(Appointment appointment);
    List<AppointmentDTO> mapAppointmentListToAppointmentDTOList(List<Appointment> appointmentList);
    @Mapping(target = "patient", source = "appointment.patient")
    @Mapping(target = "doctor", source = "appointment.doctor")
    AppointmentDetailDTO mapAppointmentToAppointmentDetailDTO(Appointment appointment);
}

package com.tgl.cmd.appointments.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

	AppointmentMapper mapper = Mappers.getMapper(AppointmentMapper.class);
	
	//Entity to DTO
	@Mapping(target = "appointmentDate", expression = "java(formatDate(appointment.getAppointmentDate()))")
	CreateAppointmentDTO toDTO(Appointment appointment);
	
	
	//DTO to entity
	@Mapping(target = "appointmentDate", expression = "java(parseDate(dto.getAppointmentDate()))")
	Appointment toAppointment(CreateAppointmentDTO dto);
	
	// Helper method to format LocalDate to dd-MM-yyyy
    default String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
    }

    // Helper method to parse dd-MM-yyyy to LocalDate
    default LocalDate parseDate(String date) {
        return date != null ? LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")) : null;
    }
}

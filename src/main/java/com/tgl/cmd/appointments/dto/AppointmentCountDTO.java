package com.tgl.cmd.appointments.dto;

import com.tgl.cmd.appointments.model.AppointmentStatus;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
public class AppointmentCountDTO {

	private String status;
	
	private Long count;
	
	public AppointmentCountDTO(AppointmentStatus  appointmentStatus, Long count) {
        this.status = appointmentStatus.name();  // Convert enum to string
        this.count = count;
    }
}

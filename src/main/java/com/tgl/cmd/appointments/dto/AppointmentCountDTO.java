package com.tgl.cmd.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentCountDTO {

	private String status;
	
	private int count;
}

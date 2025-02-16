package com.tgl.cmd.appointments.dto;

import com.tgl.cmd.appointments.model.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AppointmentResponseDTO {
    private String message;
    private Appointment appointment;
}

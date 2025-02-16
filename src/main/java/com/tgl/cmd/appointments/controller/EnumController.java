package com.tgl.cmd.appointments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgl.cmd.appointments.model.AppointmentType;
import com.tgl.cmd.appointments.model.Purpose;

@RestController
@RequestMapping("api/appointments/enums")
public class EnumController {

	@GetMapping("/purposeOfVisit")
	public Purpose[] getPurposeOfVisit() {
		return Purpose.values();
	}
	
	@GetMapping("/appointmentType")
    public AppointmentType[] getAppointmentType() {
        return AppointmentType.values();
    }
}


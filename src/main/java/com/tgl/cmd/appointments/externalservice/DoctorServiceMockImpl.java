package com.tgl.cmd.appointments.externalservice;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.Days;
import com.tgl.cmd.appointments.model.Doctor;

import lombok.extern.slf4j.Slf4j;

/**
 * Mock implementation of IDoctorService for testing purposes.
 */
@Slf4j
public class DoctorServiceMockImpl implements IDoctorService {

	// Instance of Doctor class for mock implementation
	@Autowired
	Doctor doctor;
	/**
	 * Mock method to get doctor availability.
	 *
	 * @param appointment The appointment object containing doctor information.
	 * @return The availability status of the doctor.
	 */
	@Override
	public boolean getDoctorAvailabilityByDoctorApi(String doctorId) {

		// Return the doctor availability status
		return true;
	}

	/**
	 * Mock method to check doctor availability for a given time slot.
	 *
	 * @param appointment The appointment object containing doctor information.
	 * @return True if the doctor is available for the given time slot, otherwise
	 *         false.
	 */
	@Override
	public boolean checkDoctorForGivenTimeSlot(LocalDate appointmentDate, String appointmentTime, String doctorId) {

	    boolean isTimeSlotAvailable = false;

	    // Mock doctor's weekly active hours
	    List<String> slots = new ArrayList<>();
	    slots.add("10,13"); // 10 AM - 1 PM
	    slots.add("17,22"); // 5 PM - 10 PM

	    Map<Days, List<String>> map = new HashMap<>();
	    map.put(Days.MONDAY, slots);
	    map.put(Days.TUESDAY, slots);
	    map.put(Days.WEDNESDAY, slots);
	    map.put(Days.THURSDAY, slots);
	    map.put(Days.FRIDAY, slots);
	    map.put(Days.SATURDAY, slots);

	    // Get the day of the appointment
	    String appointmentDay = appointmentDate.getDayOfWeek().toString();
	    int time = LocalTime.parse(appointmentTime).getHour();

	    log.info("Checking doctor availability for " + appointmentDay + " at " + time + ":00...");

	    // Check if the doctor is available on that day
	    if (map.containsKey(Days.valueOf(appointmentDay.toUpperCase()))) {
	        for (String slot : map.get(Days.valueOf(appointmentDay.toUpperCase()))) {
	            String[] doctorAvailableTime = slot.split(",");
	            int startHour = Integer.parseInt(doctorAvailableTime[0]);
	            int endHour = Integer.parseInt(doctorAvailableTime[1]);

	            // ✅ Correct condition: Check if the time is within the slot
	            if (time >= startHour && time < endHour) {
	                isTimeSlotAvailable = true;
	                break;
	            }
	        }
	    } else {
	        log.info("Doctor is not available on " + appointmentDay);
	    }

	    log.info("Doctor available slots on " + appointmentDay + ": " + map.getOrDefault(Days.valueOf(appointmentDay.toUpperCase()), new ArrayList<>()));
	    log.info("Requested time: " + time);
	    log.info("Doctor availability check result: " + isTimeSlotAvailable);

	    return isTimeSlotAvailable;
	}


	@Override
	public Doctor getDoctorById(String doctorId) {
		if(doctor==null) {
			doctor = new Doctor();
		}
		doctor.setDoctorId(doctorId);
		doctor.setDoctorName("Dcotor_Name");
		doctor.setDoctorEmail("doctor@email.com");
		doctor.setAssociatedClinicId("CLI-1234-2024");
		doctor.setAssociatedClinicName("Clinic_Name");
		return doctor;
	}

}

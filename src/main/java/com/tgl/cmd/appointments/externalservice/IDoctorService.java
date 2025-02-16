package com.tgl.cmd.appointments.externalservice;

import java.time.LocalDate;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.Doctor;

/**
 * Interface for Doctor Service.
 */
public interface IDoctorService {

    /**
     * Method to get doctor availability by calling the doctor API.
     *
     * @param appointment The appointment object containing doctor information.
     * @return True if the doctor is available, otherwise false.
     */
    boolean getDoctorAvailabilityByDoctorApi(String doctorId);
        
    /**
     * Method to check doctor availability for a given time slot.
     *
     * @param appointment The appointment object containing doctor information.
     * @return True if the doctor is available for the given time slot, otherwise false.
     */
    boolean checkDoctorForGivenTimeSlot(LocalDate appointmentDate, String appointmentTime, String doctorId);
    
    Doctor getDoctorById(String doctorId);
}

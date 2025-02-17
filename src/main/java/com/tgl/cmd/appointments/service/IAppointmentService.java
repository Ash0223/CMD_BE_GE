package com.tgl.cmd.appointments.service;

import java.util.List;


import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.dto.AppointmentCountDTO;

import com.tgl.cmd.appointments.model.Appointment;

/**
 * Service interface for managing appointments.
 */
public interface IAppointmentService {

    /**
     * Method to schedule an appointment.
     * 
     * @param appointment The appointment to be scheduled.
     * @return The scheduled appointment.
     */
    public Appointment scheduleAppointment(CreateAppointmentDTO appointment);
    
    /**
     * Method to retrieve an appointment by ID.
     * 
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    public Appointment getAppointmentById(String appointmentId);
    
    /**
     * Method to cancel an appointment by ID.
     * 
     * @param appointmentId The ID of the appointment to cancel.
     * @param status        The status of the appointment.
     * @return The cancelled appointment.
     */
    public Appointment cancelAppointment(String appointmentId);
    
    public Appointment rescheduleAppointment(String appointmentId, String date, String time);

	public List<Appointment> getAllAppointments(String userId);
	
	
	public List<AppointmentCountDTO> getAppointmentCounts(String userId);

}


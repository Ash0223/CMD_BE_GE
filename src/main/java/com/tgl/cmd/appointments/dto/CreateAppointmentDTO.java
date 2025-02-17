package com.tgl.cmd.appointments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tgl.cmd.appointments.model.AppointmentStatus;
import com.tgl.cmd.appointments.model.AppointmentType;
import com.tgl.cmd.appointments.model.Doctor;
import com.tgl.cmd.appointments.model.Patient;
import com.tgl.cmd.appointments.model.Purpose;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Data Transfer Object (DTO) for creating an appointment.
 * Contains all necessary fields and validation constraints for scheduling an appointment.
 */
@Data
public class CreateAppointmentDTO {

//	private String appointmentId;
	
    /**
     * The ID of the doctor for the appointment.
     * Must not be null or empty.
     */
    @NotNull(message = "Doctor ID cannot be null")
    @NotEmpty(message = "Doctor ID cannot be empty")
    private Doctor doctor;
//    private String doctorId;

    /**
     * The ID of the patient for the appointment.
     * Must not be null or empty.
     */
    @NotNull(message = "Patient ID cannot be null")
    @NotEmpty(message = "Patient ID cannot be empty")
    private Patient patient;
//    private String patientId;

    /**
     * The date of the appointment in the format YYYY-MM-DD.
     * Must match the specified pattern and cannot be null.
     */
    @NotNull(message = "Appointment date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Appointment date must be in the format DD-MM-YYYY")
    private String appointmentDate; // Stores only the date part

    /**
     * The time of the appointment in the format HH:mm.
     * Must match the specified pattern and cannot be null.
     */
    @NotNull(message = "Appointment time cannot be null")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Appointment time must be in the format HH:mm")
    private String appointmentTime; // Stores only the time part

    /**
     * The reason for the appointment.
     * Must not be null or empty.
     */
    @NotNull(message = "Reason cannot be null")
    @NotEmpty(message = "Reason cannot be empty")
    private String reason;

    /**
     * The current status of the appointment.
     * Cannot be null.
     */
    @NotNull(message = "Appointment status cannot be null")
    private AppointmentStatus appointmentStatus;

    /**
     * The purpose of the visit (e.g., consultation, allergy check).
     * Cannot be null.
     */
    @NotNull(message = "Purpose of visit cannot be null")
    private Purpose purposeOfVisit;

    /**
     * The type of appointment (e.g., online, offline).
     * Cannot be null.
     */
    @NotNull(message = "Appointment type cannot be null")
    private AppointmentType appointmentType;
    
    
    //userid  - logged in id from FE  
    @NotNull(message = "UserId cannot be null")
    @NotEmpty(message = "UserId cannot be empty")
    private String userId;
    
}

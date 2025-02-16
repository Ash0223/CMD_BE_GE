package com.tg.Doctor.dtos;

import com.tg.Doctor.models.AvailabilityMode;
import com.tg.Doctor.models.Days;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) for representing the schedule of a doctor.
 * This DTO contains information about the doctor's name, weekday, start time, end time, and doctor ID.
 * It is used to transfer doctor schedule data between different layers of the application.
 */
@Data
public class DoctorScheduleDTO {

    /**
     * The name of the doctor.
     * It cannot be null and must be between 2 and 100 characters in length.
     */
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
 
    /**
     * The weekday of the doctor's schedule.
     * It cannot be null and is represented by an enumeration of days.
     */
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Weekday cannot be null")
    private Days weekDay;

    /**
     * The start time of the doctor's schedule.
     * It must be in HH:mm format and cannot be null.
     */
    @NotNull(message = "Start time cannot be null")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$", message = "Start time must be in HH:mm format")
    private String startTime;

    /**
     * The end time of the doctor's schedule.
     * It must be in HH:mm format and cannot be null.
     */
    @NotNull(message = "End time cannot be null")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$", message = "End time must be in HH:mm format")
    private String endTime;

    /**
     * The unique identifier for the doctor.
     * It cannot be null and must be between 1 and 50 characters in length.
     */
    @NotNull(message = "Doctor ID cannot be null")
    @Size(min = 1, max = 50, message = "Doctor ID must be between 1 and 50 characters")
    private String doctorId;
    
    /**
     * The availability mode of the doctor.
     * This field cannot be null.
     */
    @NotNull(message = "Must specify the Availability Mode")
    private AvailabilityMode availabilityMode;
    
    
    
    private String clinicId;
    
    
}

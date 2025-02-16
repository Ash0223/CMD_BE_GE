package com.tgl.cmd.appointments.validators;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.tgl.cmd.appointments.exceptions.InvalidDateFormatException;
import com.tgl.cmd.appointments.exceptions.InvalidTimeFormatException;

@Component
public class Validator {
	
	public boolean isValidDateFormat(String dateStr) throws InvalidDateFormatException {
	    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    try {
	        LocalDate.parse(dateStr, formatter1);
	        return true;
	    } catch (DateTimeParseException ignored) {}

	    try {
	        LocalDate.parse(dateStr, formatter2);
	        return true;
	    } catch (DateTimeParseException e) {
	        throw new InvalidDateFormatException("Invalid date format: " + dateStr + ". Expected format is DD-MM-YYYY or YYYY-MM-DD.");
	    }
	}


    // Custom validation for time format
    public boolean isValidTimeFormat(String timeStr) throws InvalidTimeFormatException {
        try {
            LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm")); // Will throw an exception if the time format is invalid
            return true;
        } catch (Exception e) {
            throw new InvalidTimeFormatException("Invalid time format: " + timeStr + ". Expected format is HH:mm.");
        }
    }
    
    
}

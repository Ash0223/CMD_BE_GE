package com.tg.Doctor.validators;

import java.time.LocalDate;
import java.time.Period;
import com.tg.Doctor.exceptions.InvalidAgeException;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class for validating inputs in the Doctor service module.
 */
@Slf4j
public class Validator {

	    
	 /**
     * Validates the age based on the provided date of birth. Throws an exception if 
     * the age is less than 23 years or if the date of birth is null.
     *
     * @param dateOfBirth the {@link LocalDate} representing the date of birth to validate
     * @throws InvalidAgeException if the date of birth is null or the calculated age is less than 23
     */ 
	public static void validAge(LocalDate dateOfBirth) throws InvalidAgeException {
	    if (dateOfBirth == null) {
	        throw new InvalidAgeException("Date of birth cannot be null");
	    }

	    int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
	    if (age < 23) {
	        throw new InvalidAgeException("Age must be at least 23 years");
	    }
	}
	
}
   
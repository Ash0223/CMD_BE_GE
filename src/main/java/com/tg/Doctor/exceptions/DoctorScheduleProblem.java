package com.tg.Doctor.exceptions;

public class DoctorScheduleProblem extends RuntimeException{
	
	/**
     * Constructs a new DoctorScheduleException with the specified detail message.
     *
     * @param message the detail message
     */
    public DoctorScheduleProblem(String message) {
        super(message);
    }
 
    /**
     * Constructs a new DoctorScheduleException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */ 
    public DoctorScheduleProblem(String message, Throwable cause) {
        super(message, cause);
    }

}
  
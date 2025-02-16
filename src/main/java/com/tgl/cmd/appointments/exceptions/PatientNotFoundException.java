package com.tgl.cmd.appointments.exceptions;

/**
 * Exception thrown when a patient is not found.
 */
public class PatientNotFoundException extends RuntimeException {

    /**
     * Constructs a new PatientNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public PatientNotFoundException(String message) {
        super(message);
    }
}

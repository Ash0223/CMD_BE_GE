package com.tgl.cmd.appointments.exceptions;

/**
 * Exception thrown when attempting to create a patient that already exists.
 */
public class PatientAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new PatientAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public PatientAlreadyExistsException(String message) {
        super(message);
    }
}

package com.tgl.cmd.appointments.exceptions;

/**
 * Exception thrown when attempting to access a doctor who is not available.
 */
public class DoctorNotAvailableException extends RuntimeException {

    /**
     * Constructs a new DoctorNotAvailableException with the specified detail message.
     *
     * @param message the detail message
     */
    public DoctorNotAvailableException(String message) {
        super(message);
    }
}

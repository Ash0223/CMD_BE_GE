package com.tgl.cmd.appointments.exceptions;

/**
 * Exception thrown when attempting to authenticate a disabled user.
 */
public class DisabledUserException extends RuntimeException {

    /**
     * Constructs a new DisabledUserException with the specified detail message.
     *
     * @param msg the detail message
     */
    public DisabledUserException(String msg) {
        super(msg);
    }

}

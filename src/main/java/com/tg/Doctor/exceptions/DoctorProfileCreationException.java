package com.tg.Doctor.exceptions;

public class DoctorProfileCreationException extends RuntimeException {

	public DoctorProfileCreationException(String message) {
        super(message);
    }

    public DoctorProfileCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
    
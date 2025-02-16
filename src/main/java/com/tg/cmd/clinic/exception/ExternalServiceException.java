package com.tg.cmd.clinic.exception;

public class ExternalServiceException extends Exception{
	// Constructor that accepts a message
    public ExternalServiceException(String message) {
        super(message); // Call the superclass constructor with the message
    }
}

package com.tg.Doctor.exceptions;

public class BadChoiceException extends RuntimeException{
	
	/**
     * Constructs a new BadChoiceException with the specified detail message.
     *
     * @param msg the detail message
     */
    public BadChoiceException(String msg) {
        super(msg);
    }

}
    
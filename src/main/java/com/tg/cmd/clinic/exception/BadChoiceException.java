package com.tg.cmd.clinic.exception;

public class BadChoiceException extends Exception {
    
    // Constructor
    public BadChoiceException(String string) {
        super("Bad choice, choose between 'mock' and 'service'");
    }
}

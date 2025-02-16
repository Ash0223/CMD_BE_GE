package com.tgl.cmd.appointments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic Data Transfer Object (DTO) representing a response with a message and an object.
 * 
 * @param <T> the type of the object in the response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    
    // Message accompanying the response
    private String message;
    
    // Object returned in the response
    private T object;

    /**
     * Constructor for initializing message and object.
     * 
     * @param object the object to be returned in the response
     */
    public ResponseWrapper(T object) {
        this.message = "Appointment Scheduled";
        this.object = object;
    }

    /**
     * Constructor for initializing message only.
     * 
     * @param message the message to accompany the response
     */
    public ResponseWrapper(String message) {
        this.message = message;
    }
}
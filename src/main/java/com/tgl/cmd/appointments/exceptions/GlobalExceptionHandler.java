package com.tgl.cmd.appointments.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tgl.cmd.appointments.dto.ResponseWrapper;


/**
 * Global exception handler to handle exceptions thrown by controllers.
 */
//@ControllerAdvice
public class GlobalExceptionHandler {
	
    /**
     * Handles the PatientNotFoundException.
     * @param exception The exception to handle
     * @return ResponseEntity containing an error message
     */
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<ResponseWrapper> handlePatientNotFoundException(PatientNotFoundException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
	
    /**
     * Handles the PatientAlreadyExistsException.
     * @param exception The exception to handle
     * @return ResponseEntity containing an error message
     */
	@ExceptionHandler(PatientAlreadyExistsException.class)
	public ResponseEntity<ResponseWrapper> handlePatientAlreadyExistsException(PatientAlreadyExistsException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
	
    /**
     * Handles the DoctorAlreadyExistsException.
     * @param exception The exception to handle
     * @return ResponseEntity containing an error message
     */
	@ExceptionHandler(DoctorAlreadyExistsException.class)
	public ResponseEntity<ResponseWrapper> handleDoctorAlreadyExistsException(DoctorAlreadyExistsException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
	
    /**
     * Handles other runtime exceptions.
     * @param exception The exception to handle
     * @return ResponseEntity containing an error message
     */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseWrapper> handleRuntimeException(RuntimeException exception){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseWrapper(exception.getMessage()));
	}
}

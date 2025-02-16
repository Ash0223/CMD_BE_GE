package com.tg.Doctor.exceptions;

public class DoctorAlreadyExistsException extends RuntimeException{

	public DoctorAlreadyExistsException(String message) {
		super(message);
	}
}

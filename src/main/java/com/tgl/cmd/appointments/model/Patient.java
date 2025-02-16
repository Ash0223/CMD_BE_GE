package com.tgl.cmd.appointments.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Patient entity representing a patient in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Patient {

    /**
     * Status of the patient.
     */
	private String patientId;
	private String patientName;
	private String patientEmail;
	private String contactNumber;
	private int age;
    private boolean isActive;
    
	public Patient(String patientName, String patientEmail, String contactNumber, int age, boolean isActive) {
		super();
		this.patientName = patientName;
		this.patientEmail = patientEmail;
		this.contactNumber = contactNumber;
		this.age = age;
		this.isActive = isActive;
	}
    
    
}

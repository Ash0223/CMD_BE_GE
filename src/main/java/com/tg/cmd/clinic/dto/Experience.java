package com.tg.cmd.clinic.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Experience {
	@Column(name = "Clinic_Name")
	private String clinicName;
	
	@Column(name = "Experience_In_Years")
	private int experienceInYears;
	
	@Column(name = "Role")
	private String role;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Experience_Type")
	private ExperienceType experienceType;
	
}

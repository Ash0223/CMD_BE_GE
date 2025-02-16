package com.tg.Doctor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
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

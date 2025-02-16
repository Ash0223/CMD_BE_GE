package com.tg.Doctor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Qualification {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "Qualification_Type")
	private String qualificationType;
	
	@Column(name = "Degree")
	private String degree;
	
	@Column(name = "University")
	private String university;
	
	@Column(name = "Institution_Name")
	private String institutionName;
	
	@Column(name = "Star_Year")
	private String startYear;
	
	@Column(name = "End_Year")
	private String endYear;
	
	
	
	
	
	

}

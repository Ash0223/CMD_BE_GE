package com.tg.Doctor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Specialization {

	@Column(name = "specializationType")
	private String specializationType;

	@Column(name = "specializationDescription")
	private String specializationDescription;
	
	
	
	
}

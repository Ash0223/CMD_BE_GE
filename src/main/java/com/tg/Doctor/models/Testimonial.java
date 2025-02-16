package com.tg.Doctor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Testimonial {

	@Column(name = "Patient_Name")
	private String patientName;
	
	@Column(name = "Feedback")
	private String feedback;

	@Column(name = "Rating_out_of_10")
	private int rating;
	
	
	
	
	
}

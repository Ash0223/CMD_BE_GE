package com.tg.Doctor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class Address {
	
	@Column(name = "House_name")
	private String houseName;

	@Column(name = "City")
	private String city;
	
	@Column(name = "State")
	private String state;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "ZipCode")
	private String zipCode;
	
}

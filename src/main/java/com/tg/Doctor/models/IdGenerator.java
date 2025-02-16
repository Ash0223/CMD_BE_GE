package com.tg.Doctor.models;

import java.time.LocalDate; // Importing LocalDate for handling date operations
import java.util.Random; // Importing Random for generating random numbers

import org.hibernate.engine.spi.SharedSessionContractImplementor; // Importing SharedSessionContractImplementor for implementing identifier generator
import org.hibernate.id.IdentifierGenerator; // Importing IdentifierGenerator for generating unique identifiers

public class IdGenerator implements IdentifierGenerator {
	
	// Method to generate unique identifiers
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		// Generating a unique identifier format: "DOC_<random_number>_<current_year>"
		return "DOC_" + new Random().nextInt(100000) + "_" + LocalDate.now().getYear();
	}
	
}

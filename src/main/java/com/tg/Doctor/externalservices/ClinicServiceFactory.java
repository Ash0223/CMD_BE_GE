package com.tg.Doctor.externalservices;

import com.tg.Doctor.exceptions.BadChoiceException;

public class ClinicServiceFactory {
	
	public static IClinicService create(String choice) {

        IClinicService service;

        // Check the choice and create the appropriate implementation
        if ("mock".equals(choice)) {
            service = new ClinicServiceMockImpl();
        } else if ("service".equals(choice)) {
            service = new ClinicServiceImp();
        } else { 
            // Handle the case when the choice is not recognized
            // You can throw an exception or handle it in another way
            throw new BadChoiceException("Bad choice, choose between 'mock' and 'service'");
        }

        return service;
    }

}

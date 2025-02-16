// DoctorServiceFactory.java
package com.tg.cmd.clinic.externalservice;

import com.tg.cmd.clinic.exception.BadChoiceException;

//import com.tg.cmd.cmd_clinic_service.exception.BadChoiceException;

public class DoctorServiceFactory {

    /**
     * Factory method to create an instance of IDoctorService based on choice.
     *
     * @param choose the choice of service ("mock" or "service")
     * @return an instance of IDoctorService
     * @throws BadChoiceException if the choice is not recognized
     */
    public static IDoctorService create(String choice) throws BadChoiceException {

        IDoctorService service = null;

        // Check the choice and create the appropriate implementation
        if ("mock".equals(choice)) {
            service = new DoctorServiceMockImpl();
        } else if ("service".equals(choice)) {
            service = new DoctorServiceImpl();
        } else {
            // Handle the case when the choice is not recognized
            // You can throw an exception or handle it in another way
            throw new BadChoiceException("Bad choice, choose between 'mock' and 'service'");
        }

        return service;
    }
}
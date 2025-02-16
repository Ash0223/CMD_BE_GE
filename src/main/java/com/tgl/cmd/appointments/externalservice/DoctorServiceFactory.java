package com.tgl.cmd.appointments.externalservice;

import com.tgl.cmd.appointments.exceptions.BadChoiceException;

/**
 * Factory class to create instances of IDoctorService based on choice.
 */
public class DoctorServiceFactory {

    /**
     * Factory method to create an instance of IDoctorService based on choice.
     *
     * @param choose the choice of service ("mock" or "service")
     * @return an instance of IDoctorService
     * @throws BadChoiceException if the choice is not recognized
     */
    public static IDoctorService create(String choice) {

        IDoctorService service = null;

        // Check the choice and create the appropriate implementation
        if ("mock".equals(choice)) {
            service = new DoctorServiceMockImpl();
        } else if ("service".equals(choice)) {
            service = new DoctorServiceImp();
        } else {
            // Handle the case when the choice is not recognized
            // You can throw an exception or handle it in another way
            throw new BadChoiceException("Bad choice, choose between 'mock' and 'service'");
        }

        return service;
    }
}

package com.tgl.cmd.appointments.externalservice;

import com.tgl.cmd.appointments.exceptions.BadChoiceException;

/**
 * Factory class for creating instances of IPatientService.
 */
public class PatientServiceFactory {

    /**
     * Factory method to create an instance of IPatientService based on choice.
     *
     * @param choice The choice of service implementation (either "service" or "mock").
     * @return An instance of IPatientService based on the choice.
     * @throws BadChoiceException If the choice is not recognized.
     */
    public static IPatientService create(String choice) {
        
        IPatientService service = null;
        
        // Check the choice and create the appropriate implementation
        if (choice.equalsIgnoreCase("service")) {
            service = new PatientServiceImpl();
        } else if (choice.equalsIgnoreCase("mock")) {
            service = new PatientServiceMockImpl();
        } else {
            // Handle the case when the choice is not recognized
            // You can throw an exception or handle it in another way
            throw new BadChoiceException("Invalid choice: " + choice);
        }
        
        return service;
    }
}

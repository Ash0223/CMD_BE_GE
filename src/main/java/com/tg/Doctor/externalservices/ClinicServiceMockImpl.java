package com.tg.Doctor.externalservices;

import com.tg.Doctor.models.Clinic;

public class ClinicServiceMockImpl implements IClinicService{
	
	 // Instance of Clinic class for mock implementation
    private Clinic clinic = new Clinic();
    
    /**
     * Mock method to get clinic availability.
     *
     * @param clinic The clinic object containing clinic information.
     * @return The availability status of the clinic.
     */
    @Override
    public boolean getClinicAvailabilityByClinicApi(String clinicId) {

        // Mock implementation to set clinic availability status
        clinic.setClinicStatus(true);

        // Return the clinic availability status
        return clinic.getClinicStatus(); 
    }


}

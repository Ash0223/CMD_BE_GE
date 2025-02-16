package com.tg.cmd.clinic.externalservice;

import com.tg.cmd.clinic.dto.DoctorDTO;
import com.tg.cmd.clinic.model.Clinic;

/**
 * Interface for Doctor Service.
 * Defines methods related to checking blood test and managing service status.
 */
public interface IDoctorService {

    /**
     * Checks if a blood test is scheduled and manages the service status accordingly.
     *
     * @param clinic the clinic for which to check blood test and manage service status
     * @return true if the blood test is scheduled and service status is managed successfully, false otherwise
     */
    

	DoctorDTO fetchDoctorInfo(String doctorId);
    
}

package com.tgl.cmd.appointments.externalservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.Doctor;
import com.tgl.cmd.appointments.model.Patient;

/**
 * A mock implementation of the {@code IPatientService} interface.
 */
public class PatientServiceMockImpl implements IPatientService {

    /** Instance of Patient class for mock implementation */
	@Autowired
	Patient patient;
    /**
     * Mock method to get patient status from patient API.
     *
     * @param appointment The appointment for which the patient status is being retrieved.
     * @return {@code true} if the patient status is active, {@code false} otherwise.
     */
    @Override
    public boolean getPatientStatusFromPatientApi(String patientId) {
        
        // Return the patient status
        return true;
    }
    
    @Override
    public Patient getPatientById(String patientId) {
        
        // Parse the API response into a Patient object
    	if(patient == null) {
    		patient = new Patient();
    	}
    	patient.setPatientId(patientId);
    	patient.setAge(41);
    	patient.setContactNumber("+19192721722");
    	patient.setPatientEmail("patient2743@patientmail.com");
    	patient.setPatientName("David Black");
    	patient.setActive(true);
        return patient;
    }
}

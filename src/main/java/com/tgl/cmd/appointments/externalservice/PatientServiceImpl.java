package com.tgl.cmd.appointments.externalservice;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.Patient;
import com.tgl.cmd.appointments.repository.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of IPatientService to interact with the patient API.
 */
@Slf4j
public class PatientServiceImpl implements IPatientService{

    // Autowired AppointmentRepository for accessing appointment data
    @Autowired
    AppointmentRepository appointmentRepository;
    
    // Autowired RestTemplate for making HTTP requests
    @Autowired
    private RestTemplate restTemplate;
    
    // ResponseEntity to hold patient API response
    private ResponseEntity<String> patientResponse;
    
    // Value annotation for injecting patient API URL from properties
    @Value("${patientApiUrl}")
    private String patientApiUrl;
    
    /**
     * Method to get patient status from the patient API.
     *
     * @param appointment The appointment object containing patient information.
     * @return The status of the patient retrieved from the patient API.
     */
    @Override
    public boolean getPatientStatusFromPatientApi(String patientId) {

        boolean status = false;
        
        // Call patient API to get patient status
        patientResponse = restTemplate.exchange(patientApiUrl + patientId, HttpMethod.GET, null, String.class);
        
        // Log patient API response
        log.info("Response" + patientResponse.getBody());
        
        // Parse patient API response and extract patient status
        if (patientResponse.getBody() != null) {
            JsonParser springParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = springParser.parseMap(patientResponse.getBody());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("isActive".equals(entry.getKey())) {
                    status = Boolean.parseBoolean(entry.getValue().toString());
                    break;
                }//instead of traversing go with the map.get
            }
        }
        return status;
    }
    
    
    @Override
    public Patient getPatientById(String patientId) {
        // API call to fetch patient details
        ResponseEntity<String> patientResponse = restTemplate.exchange(
            patientApiUrl + patientId, HttpMethod.GET, null, String.class);

        log.info("Response from Patient API: {}", patientResponse.getBody());

        // Parse the API response into a Patient object
        Patient patient = null;
        if (patientResponse.getBody() != null) {
            JsonParser jsonParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = jsonParser.parseMap(patientResponse.getBody());

            // Map JSON response to Patient object
            patient = new Patient();
            patient.setPatientId((String) map.get("patientId"));
            patient.setPatientName((String) map.get("patientName"));
            patient.setPatientEmail((String) map.get("patientEmail"));
            patient.setContactNumber((String) map.get("contactNumber"));
            patient.setAge((Integer) map.get("age"));
            patient.setActive(Boolean.parseBoolean(map.get("isActive").toString()));
        }

        return patient;
    }

}

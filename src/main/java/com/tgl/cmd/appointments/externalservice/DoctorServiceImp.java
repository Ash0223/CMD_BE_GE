package com.tgl.cmd.appointments.externalservice;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.Doctor;
import com.tgl.cmd.appointments.repository.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;



/**
 * Implementation of IDoctorService to interact with the doctor API.
 */
@Slf4j
public class DoctorServiceImp implements IDoctorService {

    @Autowired
    AppointmentRepository appointmentRepository;
    
    @Autowired
    private ResponseEntity<String> doctorResponse;
//    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplate;

    
    @Value("${doctorApiUrl}")
    private String doctorApiUrl;
    
    @Autowired
    Doctor doctor;
    
    /**
     * Method to check doctor availability by calling the doctor API.
     *
     * @param appointment The appointment object containing doctor information.
     * @return The availability status of the doctor retrieved from the doctor API.
     */
    @Override
    public boolean getDoctorAvailabilityByDoctorApi(String doctorId) {
    	 boolean status = false;

    	    try {
    	        // Call the doctor API to get doctor availability
    	        String url = doctorApiUrl + "/get-doctor-by-Id" + doctorId;
				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

    	        log.info("Doctor API Response: {}", response.getBody());

    	        if (response.getBody() != null) {
    	            // Parse XML response here if needed (e.g., using DOM or JAXB)
    	            // Example: Use a simple substring check or a library to process XML data
    	            if (response.getBody().contains("<status>WORKING</status>")) {
    	                status = true;
    	            }
    	        }
    	    } catch (Exception e) {
    	        log.error("Error calling doctor API: {}", e.getMessage(), e);
    	    }

    	    return status;

    	    
    	    //        boolean status = false;
//        
//        // Call the doctor API to get doctor availability
//        doctorResponse = restTemplate.exchange(doctorApiUrl + doctorId, HttpMethod.GET, null,
//                String.class);
//
//        // Log the response from the doctor API
//        log.info("Response" + doctorResponse.getBody());
//
//        // Parse the response and extract doctor availability status
//        if (doctorResponse.getBody() != null) {
//            JsonParser springParser = JsonParserFactory.getJsonParser();
//            Map<String, Object> map = springParser.parseMap(doctorResponse.getBody());
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                if ("status".equals(entry.getKey())) {
//                	if(entry.getValue().toString() == "WORKING") {
//                		status = true;
//                        break;
//                	}
//                }
//            }
//        } 
//        return status;
    }

    /**
     * Method to check doctor availability for a given time slot.
     *
     * @param appointment The appointment object containing doctor information.
     * @return The availability status of the doctor for the given time slot.
     */
    @Override
    public boolean checkDoctorForGivenTimeSlot(LocalDate appointmentDate, String appointmentTime, String doctorId) {
        try {
            // Parse appointmentDate and appointmentTime
            LocalDate date = appointmentDate;
            LocalTime time = LocalTime.parse(appointmentTime, DateTimeFormatter.ISO_TIME);
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            // Call the doctor API to get the schedule
            String apiUrl = doctorApiUrl + doctorId + "/schedule?day=" + dayOfWeek;
            ResponseEntity<String> doctorResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, null, String.class);

            log.info("Doctor API Response: {}", doctorResponse.getBody());

            if (doctorResponse.getBody() != null) {
                // Parse the API response
                JsonParser springParser = JsonParserFactory.getJsonParser();
                Map<String, Object> responseMap = springParser.parseMap(doctorResponse.getBody());

                // Extract the day's schedule from the API response
                if (responseMap.containsKey("slots")) {
                    List<String> slots = (List<String>) responseMap.get("slots");

                    // Check if the appointment time falls into any available slot
                    for (String slot : slots) {
                        String[] timeRange = slot.split("-");
                        LocalTime startTime = LocalTime.parse(timeRange[0]);
                        LocalTime endTime = LocalTime.parse(timeRange[1]);

                        if (!time.isBefore(startTime) && !time.isAfter(endTime)) {
                            return true; // Slot is available
                        }
                    }
                }
            }

            // If no slot matches, return false
            return false;

        } catch (Exception e) {
            log.error("Error checking doctor's availability for time slot: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public Doctor getDoctorById(String doctorId) {
    	if(doctor==null) {
			doctor = new Doctor();
		}
        doctor.setDoctorId(doctorId); // Set the doctorId from the method argument
        
        try {
            // Call the doctor API to get doctor availability
            String url = doctorApiUrl + "/get-doctor-by-Id/" + doctorId;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

            log.info("Doctor API Response: {}", response.getBody());

            if (response.getBody() != null) {
                // Parse XML response
                String xmlResponse = response.getBody();
                
                // Parse XML using a library like JAXB, DOM, or a simpler method
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputStream is = new ByteArrayInputStream(xmlResponse.getBytes());
                Document document = builder.parse(is);
                
                // Extract data from XML
                Element root = document.getDocumentElement();
                doctor.setDoctorName(getTagValue("doctorName", root));
                doctor.setDoctorEmail(getTagValue("email", root));
                doctor.setAssociatedClinicId(getTagValue("clinicId", root));
                doctor.setAssociatedClinicName(getTagValue("clinicName", root));
            }
        } catch (Exception e) {
            log.error("Error calling doctor API: {}", e.getMessage(), e);
        }

        return doctor;
    }

    /**
     * Helper method to extract the text content of a specific XML tag
     */
    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                return node.getTextContent();
            }
        }
        return null;
    }


}

package com.tg.cmd.clinic.externalservice;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import com.tg.cmd.clinic.dto.DoctorDTO;
import com.tg.cmd.clinic.exception.ExternalServiceException;
import com.tg.cmd.clinic.model.Clinic;
import com.tg.cmd.clinic.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Value;
@Slf4j
@Service
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @PostConstruct
    public void init() {
        if (restTemplate != null) {
            log.info("RestTemplate is successfully injected.");
        } else {
            log.error("RestTemplate injection failed.");
        }
    }
    
    
    private static final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

	

   
    

    @Value("${external.api.url}")
    private String externalApiUrl;
    
    
    
    @Override
    public DoctorDTO fetchDoctorInfo(String doctorId) {
       // String url = externalApiUrl;  //+ doctorId;
    	externalApiUrl="https://app-doctors-service.azurewebsites.net";
    	System.out.println("-_-_-_-_-_-_-_-_-_-_-__---_-_"+externalApiUrl);
    	String url = externalApiUrl + "/api/doctors/" + doctorId + "/get-doctor-by-Id";
    	System.out.println("-_-_-_-_-_-_-_-_-_-_-__---_-_"+url);
        try {
        	HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("admin", "admin123");
            HttpEntity<?> entity = new HttpEntity<>(headers);
          ResponseEntity<DoctorDTO> response = restTemplate.exchange(url,HttpMethod.GET, entity ,DoctorDTO.class);
           // ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity ,String.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new ExternalServiceException("Failed to fetch doctor information");
            }
        } catch (ExternalServiceException e) {
            // Handle the exception (e.g., log the error and return a fallback)
            System.err.println("Error fetching doctor info: " + e.getMessage());
            return null; // Or return a default DoctorDTO object
        }
    }

    
}

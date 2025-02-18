package com.tg.cmd.clinic.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tg.cmd.clinic.dto.ClinicDTO;
import com.tg.cmd.clinic.dto.DoctorDTO;
import com.tg.cmd.clinic.model.Clinic;

public interface ClinicService {

    // Get all services
    List<ClinicDTO> viewAllClinic();
  
    Page<Clinic> viewAllClinicWithPagination(Pageable pageable);

    // Get service by ID
    Clinic vewClinicId(String id);

    // Create a new service
    Clinic addClinic(Clinic clinic);

    // Method to update an existing service
    Clinic updateService(String id, Clinic clinic);

     // Method to delete a service
     void deleteService(String id);
     
     DoctorDTO fetchDoctorInfoByClinicId(String clinicId);

	
    
    

}

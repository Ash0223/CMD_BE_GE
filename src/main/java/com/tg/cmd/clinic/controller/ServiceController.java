package com.tg.cmd.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.tg.cmd.clinic.dto.ClinicDTO;

import com.tg.cmd.clinic.dto.DoctorDTO;
import com.tg.cmd.clinic.mappers.ClinicMappers;
import com.tg.cmd.clinic.model.Clinic;

import com.tg.cmd.clinic.service.ClinicService;

//import io.swagger.v3.oas.models.media.MediaType;
//import org.springframework.http.MediaType;
import java.util.List;




/**
 * REST Controller for managing Clinic services.
 * Provides endpoints to create, retrieve, update, and delete clinic services.
 */
@RestController
@RequestMapping("/api/Clinic")
public class ServiceController {

    @Autowired
    private ClinicService clinicService;
    

    /**
     * Retrieves all clinic services.
     * 
     * @return A ResponseEntity containing a list of ClinicDTOs or an error response.
     */
    @GetMapping("/get-all-service")
  //  @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClinicDTO>> viewAllClinic() {
        try {
            // Retrieve the list of clinics
            List<ClinicDTO> clinicDTOs = clinicService.viewAllClinic();

            // Return the list of ClinicDTOs in the response
            return ResponseEntity.ok(clinicDTOs);
        } catch (Exception e) {
            // Handle any unexpected errors by returning an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/get-list-of-services")
    //  @PreAuthorize("hasRole('USER')")
      public ResponseEntity<?> viewAllClinicWithPagination(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size) {
          try {
              // Retrieve the list of clinics
        	  Pageable pageable = PageRequest.of(page, size);
              Page<Clinic> clinics = clinicService.viewAllClinicWithPagination(pageable);

              // Return the list of ClinicDTOs in the response
              return new ResponseEntity<>(clinics,HttpStatus.OK);
          } catch (Exception e) {
              // Handle any unexpected errors by returning an internal server error response
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
          }
      }

    /**
     * Retrieves a specific clinic service by its ID.
     * 
     * @param id The ID of the clinic to retrieve.
     * @return A ResponseEntity containing the Clinic object or a not-found/error response.
     */
    @GetMapping("/service/{id}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Clinic> vewClinicId(@PathVariable String id) {
        try {
            // Call the service to retrieve a clinic by ID
            Clinic clinic = clinicService.vewClinicId(id);

            // Check if the clinic exists and return appropriate response
            if (clinic != null) {
                return ResponseEntity.ok(clinic);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle any unexpected errors by returning an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Creates a new clinic service.
     * 
     * @param clinic The Clinic object to be created.
     * @return A ResponseEntity containing a success message or an error response.
     */
    @PostMapping("/create-service")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addClinic(@RequestBody Clinic clinic) {
        try {
            // Call the service to create a new clinic
            Clinic addClinic = clinicService.addClinic(clinic);

            // Return a success message
            return ResponseEntity.status(HttpStatus.CREATED).body("Clinic created successfully  "+clinic.getId());
           
        } catch (Exception e) {
            // Handle any unexpected errors by returning an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating clinic: " + e.getMessage());
        }
    }

     
  
    /**
     * Updates an existing clinic service.
     * 
     * @param id     The ID of the clinic to be updated.
     * @param clinic The Clinic object with updated details.
     * @return A ResponseEntity containing a success message or an error response.
     */
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateService(@PathVariable String id, @RequestBody Clinic clinic) {
        try {
            // Call the service to update the clinic details
            Clinic updatedClinic = clinicService.updateService(id, clinic);

            // Return a success message
            
            return ResponseEntity.status(HttpStatus.OK).body("Clinic updated successfully "+clinic.getId());
        } catch (Exception e) {
            // Handle any unexpected errors by returning an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating clinic: " + e.getMessage());
        }
    }

    /**
     * Deletes a clinic service by its ID.
     * 
     * @param id The ID of the clinic to be deleted.
     * @return A ResponseEntity containing a success message or an error response.
     */
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteService(@PathVariable String id) {
        try {
            // Call the service to delete the clinic
            clinicService.deleteService(id);

            // Return a success message
            return ResponseEntity.ok("Clinic deleted successfully");
        } catch (Exception e) {
            // Handle any unexpected errors by returning an internal server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting clinic: " + e.getMessage());
        }
    }
    
    
    
    @GetMapping("/{clinicId}/doctor-info")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<DoctorDTO> getDoctorInfo(@PathVariable String clinicId) {
        DoctorDTO doctorDTO = clinicService.fetchDoctorInfoByClinicId(clinicId);
        return ResponseEntity.ok(doctorDTO);
    }
}




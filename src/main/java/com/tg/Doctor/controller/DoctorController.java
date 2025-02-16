package com.tg.Doctor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tg.Doctor.dtos.DoctorDTO;
import com.tg.Doctor.exceptions.DoctorAlreadyExistsException;
import com.tg.Doctor.models.Address;
import com.tg.Doctor.models.Doctor;
import com.tg.Doctor.models.Experience;
import com.tg.Doctor.service.IDoctorService;
import com.tg.Doctor.validators.Validator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * DoctorController handles HTTP requests related to doctor operations such as adding, retrieving,
 * updating, and deleting doctors. It exposes various API End points for managing doctor information,
 * including personal details, address, experience, and scheduling.
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired  
    private IDoctorService doctorService; 
  
    /**
     * End point to add a new doctor to the system.
     * 
     * @param doctorDTO the DoctorDTO containing doctor details.
     * @return ResponseEntity with status 200 OK if successful, or 500 INTERNAL_SERVER_ERROR if an error occurs.
     */
    @PostMapping("/addDoctor")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        try {
            Validator.validAge(doctorDTO.getDateOfBirth());
            log.info("Age is valid, doctor is approved!");
            doctorService.addDoctor(doctorDTO);
            return ResponseEntity.ok("Data saved successfully"); 
        }catch (DoctorAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }

    /**
     * End point to retrieve all doctors in the system.
     * 
     * @return ResponseEntity containing the list of doctors or an error message if an exception occurs.
     */
    @GetMapping(value = "/get-all-doctors", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getAllDoctors() { 
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching doctors: " + e.getMessage());
        }
    }
 
    /**
     * End point to retrieve a doctor by their ID.
     * 
     * @param doctorId the ID of the doctor.
     * @return ResponseEntity containing the doctor details or a 404 NOT_FOUND status if the doctor does not exist.
     */
    @GetMapping("/get-doctor-by-Id/{doctorId}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getDoctorById(@PathVariable String doctorId) {
        try {
            Doctor doctor = doctorService.viewDoctorId(doctorId);
            if (doctor != null) {
                return new ResponseEntity<>(doctor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching doctor: " + e.getMessage());
        }
    }

    /**
     * End point to update the address of a doctor.
     * 
     * @param doctorId the ID of the doctor to update.
     * @param address the new address details for the doctor.
     * @return ResponseEntity containing the updated doctor details or a 404 NOT_FOUND status if the doctor does not exist.
     */
    @PutMapping("/update-doctor-address/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAddress(@PathVariable String doctorId, @RequestBody Address address) {
        try {
            Doctor doctor = doctorService.updateAddress(doctorId, address);
            if (doctor != null) {
                return new ResponseEntity<>(doctor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating the address: " + e.getMessage());
        }
    }

    /**
     * End point to delete a doctor by their ID.
     * 
     * @param doctorId the ID of the doctor to delete.
     * @return ResponseEntity with a status of 200 OK if successful or 404 NOT_FOUND if the doctor does not exist.
     */
    @DeleteMapping("/delete-doctor/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDoctorById(@PathVariable String doctorId) {
        try {
            doctorService.deleteDoctor(doctorId);
            if (doctorId != null) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting the doctor by ID: " + doctorId + e.getMessage());
        }
    }

    /**
     * End point to update a doctor's experience.
     *  
     * @param doctorId the ID of the doctor to update.
     * @param experiences the list of new experience details for the doctor.
     * @return ResponseEntity with status 200 OK if successful, or 404 NOT_FOUND if the doctor does not exist.
     */
    @PutMapping("/update-doctor-experience/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> updateExperience(@PathVariable String doctorId, @RequestBody List<Experience> experiences) {
        try {
            Doctor updatedDoctor = doctorService.updateExperience(doctorId, experiences);
            System.out.println(updatedDoctor);
            if (doctorId != null) {
                return ResponseEntity.ok(updatedDoctor);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } 
}

package com.tg.Doctor.controller;

import com.tg.Doctor.dtos.DoctorScheduleDTO;
import com.tg.Doctor.exceptions.DoctorScheduleProblem;
import com.tg.Doctor.models.DoctorSchedule;
import com.tg.Doctor.service.IDoctorScheduleService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing doctor schedules.
 * Provides RESTful endpoints for creating, retrieving, updating, and deleting doctor schedules.
 */
@RestController
@RequestMapping("/api/doctorschedule")
public class DoctorScheduleController {

    @Autowired
    private IDoctorScheduleService doctorScheduleService;

    /**
     * Endpoint for creating a new doctor schedule. 
     * Only accessible by users with the 'ADMIN' role.
     * 
     * @param doctorScheduleDTO The doctor schedule data to be created.
     * @return A ResponseEntity with a success or error message.
     */
    @PostMapping("/add-schedule")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createDoctorSchedule(@Valid @RequestBody DoctorScheduleDTO doctorScheduleDTO) {
        try {
            doctorScheduleService.createDoctorSchedule(doctorScheduleDTO);
            return ResponseEntity.ok("Schedule created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Doctor schedule sharing failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all doctor schedules.
     * Accessible by any authenticated user.
     * 
     * @return A ResponseEntity containing the list of doctor schedules or an error message.
     */
    @GetMapping("/get-all-doctor-schedules")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllDoctorSchedules() {
        try {
            Iterable<DoctorSchedule> doctorSchedules = doctorScheduleService.getAllDoctorSchedules();
            return new ResponseEntity<>(doctorSchedules, HttpStatus.OK);
        } catch (DoctorScheduleProblem e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching doctor schedules: " + e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving a specific doctor schedule by ID.
     * Accessible by users with 'ADMIN' or 'USER' roles.
     * 
     * @param doctorId The ID of the doctor whose schedule is to be retrieved.
     * @return A ResponseEntity containing the doctor schedule or a 'Not Found' response.
     */
    @GetMapping("/get-schedule-by-Id/{doctorId}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getDoctorScheduleById(@PathVariable String doctorId) {
        try {
            DoctorSchedule doctorSchedule = doctorScheduleService.getDoctorScheduleById(doctorId);
            if (doctorSchedule != null) {
                return new ResponseEntity<>(doctorSchedule, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DoctorScheduleProblem e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching doctor schedule: " + e.getMessage());
        }
    }
    
    /**
     * Endpoint for updating an existing doctor schedule.
     * Accessible by users with 'ADMIN' or 'USER' roles.
     * 
     * @param doctorScheduleDTO The updated doctor schedule data.
     * @return A ResponseEntity containing the updated doctor schedule or an error message.
     */
    @PutMapping("/update-doctor-schedule/{doctorId}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> updateDoctorSchedule(@RequestBody DoctorScheduleDTO doctorScheduleDTO){
        try {    		
            DoctorSchedule doctorSchedule = doctorScheduleService.updateDoctorSchedule(doctorScheduleDTO);
            return new ResponseEntity<>(doctorSchedule, HttpStatus.OK);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Doctor schedule updating failed: " + e.getMessage());
        }
    }

    /**
     * Endpoint for deleting a doctor schedule.
     * Only accessible by users with the 'ADMIN' role.
     * 
     * @param doctorId The ID of the doctor whose schedule is to be deleted.
     * @return A ResponseEntity with a success or error message.
     */
    @DeleteMapping("/delete-doctor-schedule/{doctorId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDoctorSchedule(@PathVariable String doctorId){
        try {    		
            doctorScheduleService.deleteDoctorSchedule(doctorId);
            return ResponseEntity.ok("Schedule Deleted Successfully");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed deleting doctor schedule: " + e.getMessage()); 
        }
    } 
}

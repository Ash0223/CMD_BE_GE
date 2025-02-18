package com.tgl.cmd.appointments.controller;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tgl.cmd.appointments.dto.AppointmentCountDTO;
import com.tgl.cmd.appointments.dto.AppointmentResponseDTO;
import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.dto.ResponseWrapper;
import com.tgl.cmd.appointments.exceptions.DoctorNotAvailableException;
import com.tgl.cmd.appointments.exceptions.InvalidDateFormatException;
import com.tgl.cmd.appointments.exceptions.InvalidTimeFormatException;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.service.IAppointmentService;
import com.tgl.cmd.appointments.validators.Validator;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing appointment-related endpoints.
 * Provides RESTful APIs for scheduling, retrieving, canceling, 
 * rescheduling, and listing appointments.
 */
@Slf4j
@RestController
@RequestMapping("api/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private Validator validate;

    /**
     * Endpoint to schedule a new appointment.
     * 
     * @param appointment the appointment details to be scheduled
     * @return ResponseEntity indicating success or failure of the scheduling operation
     */
//    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDTO> scheduleAppointment(@RequestBody CreateAppointmentDTO appointment) {
        try {
            log.info("Received JSON: " + appointment.getAppointmentDate());
            System.out.println(appointment.getAppointmentDate());

            // Validate date and time format
            validate.isValidDateFormat(appointment.getAppointmentDate());
            validate.isValidTimeFormat(appointment.getAppointmentTime());

            Appointment newAppointment = appointmentService.scheduleAppointment(appointment);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AppointmentResponseDTO("Appointment scheduled successfully", newAppointment));

        } catch (DateTimeParseException e) {
            log.error("Invalid date/time format: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AppointmentResponseDTO("Invalid date or time format", null));

        } catch (DoctorNotAvailableException e) {
            log.error("Doctor is not available: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AppointmentResponseDTO("Doctor is not available at the selected time", null));
  
        } catch (Exception e) {
        	
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AppointmentResponseDTO("Appointment scheduled Unsuccessfully "+e.getMessage(), null));	          
        }
    }


    /**
     * Retrieves an appointment by its ID.
     * <p>
     * This endpoint returns the details of an appointment based on the provided appointment ID. 
     * If the appointment is found, it wraps the appointment data in a {@link ResponseWrapper} object 
     * and returns a 200 OK response. If the appointment is not found, a 404 Not Found response is returned 
     * with an appropriate error message.
     * </p>
     * 
     * @param appointmentId the ID of the appointment to retrieve
     * @return a {@link ResponseEntity} containing a {@link ResponseWrapper} with the appointment details
     *         or an error message if not found
     */
    @GetMapping(value = "/get-by-id/{appointmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseWrapper<Appointment>> getAppointmentById(@PathVariable("appointmentId") String appointmentId) {
        Appointment appointmentResponse = this.appointmentService.getAppointmentById(appointmentId);
        
        if (appointmentResponse != null) {
            // Wrap the Appointment object in ResponseWrapper
            ResponseWrapper<Appointment> responseWrapper = new ResponseWrapper<>(appointmentResponse);
            return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
        } else {
            // Return a 404 if the appointment is not found
            ResponseWrapper<Appointment> errorResponse = new ResponseWrapper<>("Appointment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    /**
     * Endpoint to cancel an appointment by its ID.
     * 
     * @param appointmentId the unique identifier of the appointment to be canceled
     * @return ResponseEntity containing the updated appointment details wrapped in a ResponseWrapper
     */
    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<ResponseWrapper> cancelAppointment(@PathVariable("appointmentId") String appointmentId) {
        Appointment appointmentResponse = appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(appointmentResponse));
    }

    /**
     * Endpoint to reschedule an existing appointment by its ID.
     * 
     * @param appointmentId the unique identifier of the appointment to be rescheduled
     * @param newDate the new date for the appointment
     * @param newTime the new time for the appointment
     * @return ResponseEntity containing the updated appointment details wrapped in a ResponseWrapper
     */
    @PutMapping("/reschedule-appointment/{appointmentId}")
    public ResponseEntity<ResponseWrapper> rescheduleAppointment(@PathVariable("appointmentId") String appointmentId, 
    		@RequestParam("newDate") String newDate, 
            @RequestParam("newTime") String newTime) {
    	try {
    		
    		System.out.println("Received Date: " + newDate);
    		System.out.println("Received Time: " + newTime);

            validate.isValidDateFormat(newDate);
            validate.isValidTimeFormat(newTime);
        } catch (InvalidDateFormatException | InvalidTimeFormatException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper("Invalid date or time format"));
        }
        Appointment appointmentResponse = appointmentService.rescheduleAppointment(appointmentId, newDate, newTime);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper(appointmentResponse));
    }
    
    

    /**
     * Endpoint to retrieve all appointments.
     * 
     * @return ResponseEntity containing a list of all appointments
     */
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get-all-appointments/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Appointment>> getAllAppointments(@PathVariable("userId") String userId) {
        List<Appointment> appointments = this.appointmentService.getAllAppointments(userId);
        return ResponseEntity.ok(appointments);
    }
    
//    @GetMapping(value = "/get-list-of-appointments", produces = MediaType.APPLICATION_JSON_VALUE)
////  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//  public ResponseEntity<?> getAllAppointmentsWithPaginationWithPagination(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size) { 
//      try {
//      	Pageable pageable = PageRequest.of(page,size);
//          Page<Appointment> appointments = appointmentService.getAllAppointmentsWithPagination(pageable);
//          return new ResponseEntity<>(appointments, HttpStatus.OK);
//      } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching appointments: " + e.getMessage());
//      }
//  }
    
    @GetMapping(value = "/get-list-of-appointments/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  public ResponseEntity<?> getAllAppointmentsWithPaginationWithPagination(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size,@PathVariable("userId") String userId) { 
      try {
      	Pageable pageable = PageRequest.of(page,size);
          Page<Appointment> appointments = appointmentService.getAllAppointmentsWithPagination(pageable, userId);
          return new ResponseEntity<>(appointments, HttpStatus.OK);
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching appointments: " + e.getMessage());
      }
  }
    
    @GetMapping(value = "/get-appointment-counts/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<AppointmentCountDTO>> getAppointmentCounts(@PathVariable("userId") String userId) {
        List<AppointmentCountDTO> appointments = this.appointmentService.getAppointmentCounts(userId);
        return ResponseEntity.ok(appointments);
    }
    
      
    
}

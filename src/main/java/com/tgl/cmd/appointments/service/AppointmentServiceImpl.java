package com.tgl.cmd.appointments.service;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.exceptions.AppointmentAlreadyExistsException;
import com.tgl.cmd.appointments.exceptions.AppointmentNotFoundException;
import com.tgl.cmd.appointments.exceptions.DoctorNotAvailableException;
import com.tgl.cmd.appointments.exceptions.InvalidDateException;
import com.tgl.cmd.appointments.exceptions.InvalidDateFormatException;
import com.tgl.cmd.appointments.exceptions.InvalidTimeFormatException;
import com.tgl.cmd.appointments.externalservice.DoctorServiceFactory;
import com.tgl.cmd.appointments.externalservice.IDoctorService;
import com.tgl.cmd.appointments.externalservice.IPatientService;
import com.tgl.cmd.appointments.externalservice.PatientServiceFactory;
import com.tgl.cmd.appointments.mappers.AppointmentMapper;
import com.tgl.cmd.appointments.model.Appointment;
import com.tgl.cmd.appointments.model.AppointmentStatus;
import com.tgl.cmd.appointments.model.AppointmentType;
import com.tgl.cmd.appointments.model.Doctor;
import com.tgl.cmd.appointments.model.Patient;
import com.tgl.cmd.appointments.repository.AppointmentRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the {@link IAppointmentService} interface.
 */
@Slf4j
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private AppointmentMapper appointmentMapper;
    
    private IDoctorService doctorService;
    private IPatientService patientService;

    public AppointmentServiceImpl() {
		// TODO Auto-generated constructor stub
    	doctorService = DoctorServiceFactory.create("mock");
    	patientService = PatientServiceFactory.create("mock");
	}
    
    /**
     * Schedules a new appointment.
     * 
     * @param appointment The appointment to be scheduled.
     * @return The scheduled appointment.
     */
    @Override
    public Appointment scheduleAppointment(CreateAppointmentDTO appointmentDTO) {
        log.info("Scheduling Appointment...");
        
        boolean patientStatus = false;
        boolean doctorStatus = false;
        boolean isDoctorAvailableInSlot = false;

        if (checkPatientStatus(appointmentDTO.getPatient().getPatientId())) {
            log.info("Patient is active");
            patientStatus = true;
        } else {
            log.info("Patient is not active");
        }

        if (checkIfDoctorIsAvailable(appointmentDTO.getDoctor().getDoctorId())) {
            log.info("Doctor is available");
            doctorStatus = true;
        } else {
            log.info("Doctor is not available");
            throw new DoctorNotAvailableException("Doctor is not available in clinic");
        }

        try {
			if (compareDoctorSlots(
			        parseDate(appointmentDTO.getAppointmentDate()), 
			        appointmentDTO.getAppointmentTime(), 
			        appointmentDTO.getDoctor().getDoctorId())) {
			    log.info("Doctor is available in requested slot");
			    isDoctorAvailableInSlot = true;
			} else {
			    log.info("Doctor is not available at " + appointmentDTO.getAppointmentTime());
			}
		} catch (InvalidDateFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (patientStatus && doctorStatus && isDoctorAvailableInSlot) {
            log.info("Appointment Created");
            log.info("Saving in DB...");


            log.info("Final appointment date before saving dto: " + appointmentDTO.getAppointmentDate());

            // Set additional details
            appointmentDTO.setAppointmentStatus(AppointmentStatus.SCHEDULED);
            appointmentDTO.setDoctor(doctorService.getDoctorById(appointmentDTO.getDoctor().getDoctorId()));
            appointmentDTO.setPatient(patientService.getPatientById(appointmentDTO.getPatient().getPatientId()));
            log.info("Final appointment date before saving dto 22: " + appointmentDTO.getAppointmentDate());

            // Convert DTO to Entity
            Appointment newAppointmentRequest = appointmentMapper.toAppointment(appointmentDTO);

            log.info("Saved to DB.");
            log.info("Final appointment date before saving appp: " + newAppointmentRequest.getAppointmentDate());

            return appointmentRepository.save(newAppointmentRequest);
        } else {
            throw new AppointmentNotFoundException("Bad data for scheduling appointment");
        }
    }


    /**
     * Retrieves an appointment by its ID.
     * 
     * @param appointmentId The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    @Override
    public Appointment getAppointmentById(String appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    /**
     * Cancels an appointment by its ID.
     * 
     * @param appointmentId The ID of the appointment to cancel.
     * @param status        The status to set for the appointment.
     * @return The cancelled appointment.
     */
    @Override
    public Appointment cancelAppointment(String appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        log.info("Appointment is cancelled");
        return appointmentRepository.save(appointment);
    }
    
    

	@Override
	public List<Appointment> getAllAppointments() {
		return this.appointmentRepository.findAll();
	}

	
	@Override
	public Appointment rescheduleAppointment(String appointmentId, String newDate, String newTime) {
		Appointment appointment = this.appointmentRepository.findById(appointmentId).orElse(null);
		
		try {
			if (compareDoctorSlots(parseDate(newDate), newTime, appointment.getDoctor().getDoctorId())) {
			    log.info("Doctor is available in requested slot");

			    // Ensure date and time are parsed correctly
			    appointment.setAppointmentDate(parseDate(newDate)); // Using helper method
			    appointment.setAppointmentTime(parseTime(newTime)); // Using new helper method for time

			} else {
			    log.info("Doctor is not available on " + newDate + " at " + newTime);
			    throw new DoctorNotAvailableException("Doctor is not available on " + newDate + " at " + newTime);
			}
		} catch (InvalidDateFormatException | InvalidTimeFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println(appointment.getDoctor().getDoctorId());
//		appointment.setAppointmentType(AppointmentType.ONLINE);
		return this.appointmentRepository.save(appointment);
	}

    
    protected boolean checkIfDoctorIsAvailable(String doctorId) {
        log.info("Checking doctor's availability...");
        return doctorService.getDoctorAvailabilityByDoctorApi(doctorId);
    }

    protected boolean compareDoctorSlots(LocalDate appointmentDate, String appointmentTime, String doctorId) {
        log.info("Checking doctor's availability...");
        System.out.println(appointmentDate);

        return doctorService.checkDoctorForGivenTimeSlot(appointmentDate, appointmentTime, doctorId);
    }

    protected boolean checkPatientStatus(String patientId) {
        log.info("Checking patient status...");
        return patientService.getPatientStatusFromPatientApi(patientId);
    }
    
 // Helper method for parsing date
    private LocalDate parseDate(String date) throws InvalidDateFormatException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: " + date + ". Expected format is dd-MM-yyyy.");
            throw new InvalidDateFormatException("Invalid date format: " + date + ". Expected format is dd-MM-yyyy.");
        }
    }

    // Helper method for parsing time
    private LocalTime parseTime(String time) throws InvalidTimeFormatException {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            log.error("Invalid time format: " + time + ". Expected format is HH:mm.");
            throw new InvalidTimeFormatException("Invalid time format: " + time + ". Expected format is HH:mm.");
        }
    }
    

}

































































//private boolean checkExistingAppointment(CreateAppointmentDTO appointmentDTO) {
//LocalDate appointmentDate = LocalDate.parse(appointmentDTO.getAppointmentDate());
//LocalTime appointmentTime = LocalTime.parse(appointmentDTO.getAppointmentTime());
//String patientId = appointmentDTO.getPatient().getPatientId();
//String doctorId = appointmentDTO.getDoctor().getDoctorId();
//
//// Calculate the time range (appointmentTime ± 10 minutes)
//LocalTime startTime = appointmentTime.minusMinutes(10);
//LocalTime endTime = appointmentTime.plusMinutes(10);
//
//// Query the repository for existing appointments
//List<Appointment> existingAppointments = appointmentRepository.findAppointmentsByCriteria(
//  patientId, doctorId, appointmentDate, startTime, endTime
//);
//
//if (!existingAppointments.isEmpty()) {
//  // Log the existing appointment details
//  log.warn("Existing appointment found: {}", existingAppointments.get(0));
//  throw new AppointmentAlreadyExistsException(
//      "Appointment already exists with ID: " + existingAppointments.get(0).getAppointmentId()
//  );
//}
//
//return false;
//}

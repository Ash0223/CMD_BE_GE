package com.tg.Doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // Importing Autowired for dependency injection
import org.springframework.stereotype.Service; // Importing Service annotation to indicate this class as a service

import com.tg.Doctor.dtos.DoctorScheduleDTO;
import com.tg.Doctor.exceptions.ClinicNotAvailableByThisId;
import com.tg.Doctor.exceptions.DoctorScheduleProblem;
import com.tg.Doctor.externalservices.ClinicServiceFactory;
import com.tg.Doctor.externalservices.IClinicService;
import com.tg.Doctor.mappers.DoctorMapper;
import com.tg.Doctor.models.Clinic;
import com.tg.Doctor.models.DoctorSchedule;
import com.tg.Doctor.repositories.DoctorScheduleRepository; // Importing AppointmentRepository for data access

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for managing doctor schedules.
 * This class provides CRUD operations to create, retrieve, update, and delete doctor schedules.
 * It interacts with the DoctorScheduleRepository for data persistence.
 */
@Slf4j
@Service // Indicates that this class is a service component
public class DoctorScheduleServiceImpl implements IDoctorScheduleService {
 
    @Autowired // Injects DoctorScheduleRepository instance
    private DoctorScheduleRepository doctorScheduleRepository;
    
    private final IClinicService clinicService;
    
    @Autowired
    private DoctorMapper doctorMapper;

     
    public DoctorScheduleServiceImpl() {
    	this.clinicService = ClinicServiceFactory.create("mock");
    }
    
    
    
    /**
     * Creates a new doctor schedule.
     * Maps the provided DoctorScheduleDTO to a DoctorSchedule object and saves it to the repository.
     * 
     * @param doctorScheduleDTO the doctor schedule data transfer object containing the schedule details
     * @return the saved DoctorSchedule object
     * @throws DoctorScheduleProblem if an error occurs while saving the doctor schedule
     */
    @Override
    public DoctorSchedule createDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO) {
        try {
        	boolean isClinicAvailable = clinicService.getClinicAvailabilityByClinicApi(doctorScheduleDTO.getClinicId());
        	
        	if(!isClinicAvailable) {
        		log.info("Clinic is not available for this ID");
        		throw new ClinicNotAvailableByThisId("Clinic is not available for this ID");
        	}
            DoctorSchedule doctorSchedule = doctorMapper.toDoctorSchedule(doctorScheduleDTO);
            return doctorScheduleRepository.save(doctorSchedule);
        } catch (Exception ex) {
            // Log the error or handle it as per your requirement
            log.error("Error while sharing doctor schedule: {}", ex.getMessage(), ex);
            throw new DoctorScheduleProblem("Error while sharing doctor schedule", ex);
        }
    }

    /**
     * Retrieves a doctor schedule by its ID.
     * 
     * @param doctorId the unique ID of the doctor schedule to retrieve
     * @return the doctor schedule if found, or null if no schedule exists for the given ID
     * @throws DoctorScheduleProblem if an error occurs while fetching the doctor schedule by ID
     */
    @Override
    public DoctorSchedule getDoctorScheduleById(String doctorId) {
        try {
            return doctorScheduleRepository.findById(doctorId).orElse(null);
        } catch (Exception ex) {
            log.error("Error while fetching doctor schedule by ID: {}", ex.getMessage(), ex);
            throw new DoctorScheduleProblem("Error while fetching doctor schedule by ID", ex);
        }
    }

    /**
     * Retrieves all doctor schedules.
     * 
     * @return a list of all doctor schedules
     * @throws DoctorScheduleProblem if an error occurs while fetching all doctor schedules
     */
    @Override
    public List<DoctorSchedule> getAllDoctorSchedules() {
        try {
            return doctorScheduleRepository.findAll();
        } catch (Exception ex) {
            log.error("Error while fetching all doctor schedules: {}", ex.getMessage(), ex);
            throw new DoctorScheduleProblem("Error while fetching all doctor schedules", ex);
        }
    }

    /**
     * Updates an existing doctor schedule.
     * 
     * @param doctorScheduleDTO the updated doctor schedule data transfer object
     * @return the updated DoctorSchedule object
     */
    @Override
    public DoctorSchedule updateDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO) {
    	try {    		
    		DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(doctorScheduleDTO.getDoctorId()).orElse(null);
    		doctorSchedule = doctorMapper.toDoctorSchedule(doctorScheduleDTO);
    		return doctorScheduleRepository.save(doctorSchedule);
    	} catch(Exception e) {
    		 log.error("Error while updating doctor schedules: ", e.getMessage(), e);
             throw new DoctorScheduleProblem("Error while updating doctor schedules", e);
    	}
    }

    /**
     * Deletes a doctor schedule by its ID.
     * 
     * @param doctorId the unique ID of the doctor schedule to delete
     */
    @Override
    public void deleteDoctorSchedule(String doctorId) {
    	try {    		
    		DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(doctorId).orElse(null);
    		doctorScheduleRepository.delete(doctorSchedule);
    	} catch (Exception e) {
    		 log.error("Error while deleting doctor schedules: ", e.getMessage(), e);
             throw new DoctorScheduleProblem("Error while deleting doctor schedules", e);
    	}
    }

//    /**
//     * Maps a DoctorScheduleDTO to a DoctorSchedule entity.
//     * 
//     * @param doctorScheduleDTO the doctor schedule data transfer object to map
//     * @return a mapped DoctorSchedule entity
//     */
//    public DoctorSchedule mapDoctorScheduleDTO(DoctorScheduleDTO doctorScheduleDTO) {
//        DoctorSchedule doctorSchedule = new DoctorSchedule();
//        doctorSchedule.setName(doctorScheduleDTO.getName());
//        doctorSchedule.setStartTime(doctorScheduleDTO.getStartTime());
//        doctorSchedule.setEndTime(doctorScheduleDTO.getEndTime());
//        doctorSchedule.setDoctorId(doctorScheduleDTO.getDoctorId());
//        doctorSchedule.setWeekDay(doctorScheduleDTO.getWeekDay());
//        doctorSchedule.setAvailabilityMode(doctorScheduleDTO.getAvailabilityMode());
//        doctorSchedule.setClinicId(doctorScheduleDTO.getClinicId());
//        return doctorSchedule;
//    }
}

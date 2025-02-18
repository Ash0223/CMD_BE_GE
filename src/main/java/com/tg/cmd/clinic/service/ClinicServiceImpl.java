package com.tg.cmd.clinic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tg.cmd.clinic.dto.ClinicDTO;
import com.tg.cmd.clinic.dto.DoctorDTO;
import com.tg.cmd.clinic.exception.BadChoiceException;
import com.tg.cmd.clinic.externalservice.DoctorServiceFactory;
import com.tg.cmd.clinic.externalservice.IDoctorService;
import com.tg.cmd.clinic.mappers.ClinicMappers;
import com.tg.cmd.clinic.model.Clinic;
import com.tg.cmd.clinic.repository.ServiceRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;



@Service
public class ClinicServiceImpl implements ClinicService {

    private static final Logger logger = LoggerFactory.getLogger(ClinicServiceImpl.class);

    @Autowired
    private ServiceRepository serviceRepository;

    private IDoctorService doctorService; 
    
    @Autowired
    private ClinicMappers clinicMapper;

    /**
     * Constructor for ServiceServiceImpl.
     * Initializes the doctor service based on the choice provided.
     *
     * @throws BadChoiceException if the choice is invalid
     */
    public ClinicServiceImpl() throws BadChoiceException {
        // Initialize doctor service with mock implementation
        doctorService = DoctorServiceFactory.create("mock");
    }

    /**
     * Adds a new clinic to the system.
     *
     * @param clinic the clinic to be added
     * @return the saved clinic object
     * @throws IllegalArgumentException if the clinic data is invalid
     * @throws RuntimeException         if there is an error during the operation
     */
    @Override
    public Clinic addClinic(Clinic clinic) {
        try {
            logger.info("Creating new clinic: {}", clinic);

            // Validate Clinic Name
            if (clinic.getName() == null || clinic.getName().isEmpty()) {
                throw new IllegalArgumentException("Clinic name cannot be empty");
            }

            // Validate Phone Number format (example: must be 10 digits)
            if (clinic.getPhoneNumber() == null || !clinic.getPhoneNumber().matches("\\d{10}")) {
                throw new IllegalArgumentException("Invalid phone number format. Must be 10 digits.");
            }

            // Validate Email format
            if (clinic.getEmail() == null || !clinic.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new IllegalArgumentException("Invalid email format.");
            }
            

            // Ensure End Time is greater than Start Time
            if (clinic.getStartTime() != null && clinic.getEndTime() != null &&
                    clinic.getEndTime().isBefore(clinic.getStartTime())) {
                throw new IllegalArgumentException("Closing hours must be after opening hours.");
            }
                    
              
              
            
            // Save the clinic if validation passes
            return serviceRepository.save(clinic);
            
            
        }catch (IllegalArgumentException e) {
            logger.error("Error creating clinic", e);
            throw e;  // Re-throw the specific validation exception
        } 
        catch (Exception e) {
            logger.error("Error creating clinic", e);
            throw new RuntimeException("Error creating clinic: " + e.getMessage());
        }
    }

    /**
     * Retrieves all clinics from the database.
     *
     * @return a list of all clinics
     * @throws RuntimeException if there is an error during the operation
     */
    @Override
    public List<ClinicDTO> viewAllClinic() {
        try {
            logger.info("Retrieving all clinics from the database");
            List<Clinic> clinics= serviceRepository.findAll();
           
            
            List<ClinicDTO> ans=clinicMapper.toClinicDTOList(clinics);
           
            
			return clinicMapper.toClinicDTOList(clinics);
            
        } catch (Exception e) {
            logger.error("Error retrieving all clinics", e);
            throw new RuntimeException("Error retrieving all clinics: " + e.getMessage());
        }
    }
    
    @Override
	public Page<Clinic> viewAllClinicWithPagination(Pageable pageable) {
    	try {
            logger.info("Retrieving all clinics from the database");
            return serviceRepository.findAll(pageable);
            
        } catch (Exception e) {
            logger.error("Error retrieving all clinics", e);
            throw new RuntimeException("Error retrieving all clinics: " + e.getMessage());
        }
	}

    /**
     * Retrieves a clinic by its ID.
     *
     * @param id the ID of the clinic to retrieve
     * @return the clinic with the specified ID, or null if not found
     * @throws RuntimeException if there is an error during the operation
     */
    @Override
    public Clinic vewClinicId(String id) {
        try {
            logger.info("Retrieving clinic with ID {} from the database", id);
            Optional<Clinic> clinic = serviceRepository.findById(id);
            return clinic.orElse(null);
        } catch (Exception e) {
            logger.error("Error retrieving clinic with ID {}", id, e);
            throw new RuntimeException("Error retrieving clinic with ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Updates an existing clinic's details.
     *
     * @param id     the ID of the clinic to update
     * @param clinic the clinic object containing updated data
     * @return the updated clinic object
     * @throws IllegalArgumentException if the clinic data is invalid or not found
     * @throws RuntimeException         if there is an error during the operation
     */
    @Override
    public Clinic updateService(String id, Clinic clinic) {
        try {
            logger.info("Updating clinic with ID {}: {}", id, clinic);
            Optional<Clinic> existingClinicOptional = serviceRepository.findById(id);
            if (existingClinicOptional.isPresent()) {
                Clinic existingClinic = existingClinicOptional.get();

                // Validate Clinic Name
                if (clinic.getName() == null || clinic.getName().isEmpty()) {
                    throw new IllegalArgumentException("Clinic name cannot be empty");
                }

                // Validate Phone Number
                if (clinic.getPhoneNumber() == null || clinic.getPhoneNumber().isEmpty()) {
                    throw new IllegalArgumentException("Phone number cannot be empty");
                }

                // Update clinic attributes (e.g., name, phone number)
                if (clinic.getName() != null) {
                    existingClinic.setName(clinic.getName());
                }
                if (clinic.getPhoneNumber() != null) {
                    existingClinic.setPhoneNumber(clinic.getPhoneNumber());
                }
                // This is a placeholder for actual update logic
                return serviceRepository.save(existingClinic);
            }else{
                throw new IllegalArgumentException("Clinic with ID " + id + " not found");
            }
        }catch (IllegalArgumentException e) {
            logger.error("Error updating clinic", e);
            throw e;  // Re-throw the specific validation exception
        }catch (Exception e) {
        
            logger.error("Error updating clinic with ID {}", id, e);
            throw new RuntimeException("Error updating clinic with ID " + id + ": " + e.getMessage());
        }
    }

    /**
     * Deletes a clinic by its ID.
     *
     * @param id the ID of the clinic to delete
     * @throws RuntimeException if there is an error during the operation
     */
    @Override
    public void deleteService(String id) {
        try {
            logger.info("Deleting clinic with ID {}", id);
            serviceRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting clinic with ID {}", id, e);
            throw new RuntimeException("Error deleting clinic with ID " + id + ": " + e.getMessage());
        }
    }

    
    
    @Override
    public DoctorDTO fetchDoctorInfoByClinicId(String clinicId) {
        // Fetch Clinic entity from repository
       
        		try {
                	logger.info("Checking doctor's details availability ");
                    Optional<Clinic> existingClinic = serviceRepository.findById(clinicId);
                    if (existingClinic.isPresent()) {
                        Clinic clinic = existingClinic.get();

                        List<String> doctorIds=clinic.getDoctorIds();
                        String doctorId=doctorIds.get(0);
                        
                        
                        return doctorService.fetchDoctorInfo(doctorId);
                    } else {
                        throw new IllegalArgumentException("Clinic with ID " + clinicId + " not found");
                    }
                } catch (Exception e) {
                    logger.error("Error In Cally Externall services from  clinic with ID {}", clinicId, e);
                    throw new RuntimeException("Error in  clinic with ID " + clinicId + ": " + e.getMessage());
                }
        		
        		
        		
        		
        		
        		
        		

    }
}






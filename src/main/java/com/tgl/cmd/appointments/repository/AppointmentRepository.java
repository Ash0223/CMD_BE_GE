package com.tgl.cmd.appointments.repository;

import java.time.LocalDate;


import java.time.LocalTime;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgl.cmd.appointments.dto.AppointmentCountDTO;
import com.tgl.cmd.appointments.model.Appointment;
/**
 * Spring Data JPA repository interface for the Appointment entity.
 * This interface inherits methods for CRUD operations from JpaRepository.
 * No additional methods are defined here because JpaRepository provides all necessary CRUD operations.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	

	 @Query("SELECT new com.tgl.cmd.appointments.dto.AppointmentCountDTO(a.appointmentStatus, COUNT(a)) FROM Appointment a WHERE a.userId = :userId GROUP BY a.appointmentStatus")
	    List<AppointmentCountDTO> getAppointmentCounts(@Param("userId") String userId);

	 List<Appointment> findByUserId(String userId); 
 
	 Page<Appointment> findAll(Pageable pageable);
	 
	 Page<Appointment> findByUserId(Pageable pageable, String userId);

}
 

package com.tgl.cmd.appointments.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgl.cmd.appointments.model.Appointment;
/**
 * Spring Data JPA repository interface for the Appointment entity.
 * This interface inherits methods for CRUD operations from JpaRepository.
 * No additional methods are defined here because JpaRepository provides all necessary CRUD operations.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
//	List<Appointment> getAppointmentByDate(LocalDate startDate, LocalDate endDate);
	
//	@Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId " +
//		       "AND a.doctor.doctorId = :doctorId " +
//		       "AND a.appointmentDate = :appointmentDate " +
//		       "AND a.appointmentTime BETWEEN :startTime AND :endTime")
//		List<Appointment> findAppointmentsByCriteria(
//		    @Param("patientId") String patientId,
//		    @Param("doctorId") String doctorId,
//		    @Param("appointmentDate") LocalDate appointmentDate,
//		    @Param("startTime") LocalTime startTime,
//		    @Param("endTime") LocalTime endTime
//		);

}
 
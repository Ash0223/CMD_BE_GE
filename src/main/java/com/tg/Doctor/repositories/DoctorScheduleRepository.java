package com.tg.Doctor.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.Doctor.models.DoctorSchedule;

// JpaRepository for managing Appointment entities
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, String> {
    
    // Method to find appointments by doctorId
    List<DoctorSchedule> findByDoctorId(String doctorId);
}
 
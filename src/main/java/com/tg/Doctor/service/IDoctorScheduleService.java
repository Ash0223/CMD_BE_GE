package com.tg.Doctor.service;

import java.util.List; // Importing List for handling collections

import com.tg.Doctor.dtos.DoctorScheduleDTO;
import com.tg.Doctor.models.DoctorSchedule;


// Service interface for managing appointments
public interface IDoctorScheduleService {

	DoctorSchedule createDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO);
    DoctorSchedule getDoctorScheduleById(String doctorId);
    List<DoctorSchedule> getAllDoctorSchedules();
    DoctorSchedule updateDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO);
    void deleteDoctorSchedule(String doctorId);

}

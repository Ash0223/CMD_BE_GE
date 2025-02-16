package com.tg.Doctor.service;

import java.util.List;

import com.tg.Doctor.dtos.DoctorDTO;
import com.tg.Doctor.models.Address;
import com.tg.Doctor.models.Doctor;
import com.tg.Doctor.models.Experience;

public interface IDoctorService {
	

	List<Doctor> getAllDoctors();

	Doctor addDoctor(DoctorDTO doctorDTO);

	Doctor viewDoctorId(String doctorId);
	
	Doctor updateAddress(String doctorId, Address address);
	
	void deleteDoctor(String doctorId);
	
	Doctor updateExperience(String doctorId, List<Experience> experiences );
	
	
}

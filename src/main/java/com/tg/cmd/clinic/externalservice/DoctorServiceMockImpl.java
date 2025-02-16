package com.tg.cmd.clinic.externalservice;

import java.time.LocalDate;

import com.tg.cmd.clinic.dto.DoctorDTO;
import com.tg.cmd.clinic.dto.Gender;
import com.tg.cmd.clinic.dto.Status;
import com.tg.cmd.clinic.model.Clinic;


import jakarta.validation.constraints.*;

public class DoctorServiceMockImpl implements IDoctorService {

    @Override
    public DoctorDTO fetchDoctorInfo(String doctorId) {
        // Return a mock response
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFirstName("John");
        doctorDTO.setLastName("Doe");
        doctorDTO.setPhoneNum("1234567890");
        doctorDTO.setDateOfBirth(LocalDate.of(1985, 5, 20));
        doctorDTO.setEmail("john.doe@example.com");
        doctorDTO.setExperienceInYears(10);
        doctorDTO.setSpecializationType("Cardiology");
        doctorDTO.setSpecializationDescription("Expert in heart-related diseases.");
        doctorDTO.setGender(Gender.MALE);
        doctorDTO.setStatus(Status.WORKING);
        return doctorDTO;
    }
}
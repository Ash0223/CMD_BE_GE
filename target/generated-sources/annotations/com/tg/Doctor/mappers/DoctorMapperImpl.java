package com.tg.Doctor.mappers;

import com.tg.Doctor.dtos.DoctorDTO;
import com.tg.Doctor.dtos.DoctorScheduleDTO;
import com.tg.Doctor.models.Doctor;
import com.tg.Doctor.models.DoctorSchedule;
import com.tg.Doctor.models.Experience;
import com.tg.Doctor.models.Qualification;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-18T01:35:59+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 23 (Eclipse Adoptium)"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO toDoctorDTO(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setAddress( doctor.getAddress() );
        doctorDTO.setClinicId( doctor.getClinicId() );
        doctorDTO.setDateOfBirth( doctor.getDateOfBirth() );
        doctorDTO.setEmail( doctor.getEmail() );
        doctorDTO.setExperienceInYears( doctor.getExperienceInYears() );
        List<Experience> list = doctor.getExperiences();
        if ( list != null ) {
            doctorDTO.setExperiences( new ArrayList<Experience>( list ) );
        }
        doctorDTO.setFirstName( doctor.getFirstName() );
        doctorDTO.setGender( doctor.getGender() );
        doctorDTO.setLastName( doctor.getLastName() );
        doctorDTO.setPhoneNum( doctor.getPhoneNum() );
        List<Qualification> list1 = doctor.getQualifications();
        if ( list1 != null ) {
            doctorDTO.setQualifications( new ArrayList<Qualification>( list1 ) );
        }
        doctorDTO.setSpecialization( doctor.getSpecialization() );
        doctorDTO.setStatus( doctor.getStatus() );

        return doctorDTO;
    }

    @Override
    public Doctor toDoctor(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setAddress( doctorDTO.getAddress() );
        doctor.setClinicId( doctorDTO.getClinicId() );
        doctor.setDateOfBirth( doctorDTO.getDateOfBirth() );
        doctor.setEmail( doctorDTO.getEmail() );
        doctor.setExperienceInYears( doctorDTO.getExperienceInYears() );
        List<Experience> list = doctorDTO.getExperiences();
        if ( list != null ) {
            doctor.setExperiences( new ArrayList<Experience>( list ) );
        }
        doctor.setFirstName( doctorDTO.getFirstName() );
        doctor.setGender( doctorDTO.getGender() );
        doctor.setLastName( doctorDTO.getLastName() );
        doctor.setPhoneNum( doctorDTO.getPhoneNum() );
        List<Qualification> list1 = doctorDTO.getQualifications();
        if ( list1 != null ) {
            doctor.setQualifications( new ArrayList<Qualification>( list1 ) );
        }
        doctor.setSpecialization( doctorDTO.getSpecialization() );
        doctor.setStatus( doctorDTO.getStatus() );

        return doctor;
    }

    @Override
    public DoctorScheduleDTO toDoctorScheduleDTO(DoctorSchedule doctorSchedule) {
        if ( doctorSchedule == null ) {
            return null;
        }

        DoctorScheduleDTO doctorScheduleDTO = new DoctorScheduleDTO();

        doctorScheduleDTO.setAvailabilityMode( doctorSchedule.getAvailabilityMode() );
        doctorScheduleDTO.setClinicId( doctorSchedule.getClinicId() );
        doctorScheduleDTO.setDoctorId( doctorSchedule.getDoctorId() );
        doctorScheduleDTO.setEndTime( doctorSchedule.getEndTime() );
        doctorScheduleDTO.setName( doctorSchedule.getName() );
        doctorScheduleDTO.setStartTime( doctorSchedule.getStartTime() );
        doctorScheduleDTO.setWeekDay( doctorSchedule.getWeekDay() );

        return doctorScheduleDTO;
    }

    @Override
    public DoctorSchedule toDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO) {
        if ( doctorScheduleDTO == null ) {
            return null;
        }

        DoctorSchedule doctorSchedule = new DoctorSchedule();

        doctorSchedule.setAvailabilityMode( doctorScheduleDTO.getAvailabilityMode() );
        doctorSchedule.setClinicId( doctorScheduleDTO.getClinicId() );
        doctorSchedule.setDoctorId( doctorScheduleDTO.getDoctorId() );
        doctorSchedule.setEndTime( doctorScheduleDTO.getEndTime() );
        doctorSchedule.setName( doctorScheduleDTO.getName() );
        doctorSchedule.setStartTime( doctorScheduleDTO.getStartTime() );
        doctorSchedule.setWeekDay( doctorScheduleDTO.getWeekDay() );

        return doctorSchedule;
    }
}

package com.tg.Doctor.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.tg.Doctor.dtos.DoctorDTO;
import com.tg.Doctor.dtos.DoctorScheduleDTO;
import com.tg.Doctor.models.Doctor;
import com.tg.Doctor.models.DoctorSchedule;

/**
 * Mapper interface for converting between model entities and their corresponding DTOs
 * in the Doctor service module. Uses MapStruct for implementation.
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {
	

    /**
     * An instance of the DoctorMapper, used to access mapping methods.
     */
	DoctorMapper mapperInstance = Mappers.getMapper(DoctorMapper.class);

    /**
     * Converts a {@link Doctor} entity to a {@link DoctorDTO}.
     *
     * @param doctor the {@link Doctor} entity to convert
     * @return the converted {@link DoctorDTO}
     */
	DoctorDTO toDoctorDTO(Doctor doctor);
	
	/**
     * Converts a {@link DoctorDTO} to a {@link Doctor} entity.
     *
     * @param doctorDTO the {@link DoctorDTO} to convert
     * @return the converted {@link Doctor} entity
     */
	Doctor toDoctor(DoctorDTO doctorDTO);

	 /**
     * Converts a {@link DoctorSchedule} entity to a {@link DoctorScheduleDTO}.
     *
     * @param doctorSchedule the {@link DoctorSchedule} entity to convert
     * @return the converted {@link DoctorScheduleDTO}
     */
	DoctorScheduleDTO toDoctorScheduleDTO(DoctorSchedule doctorSchedule);
	 
	/**
     * Converts a {@link DoctorScheduleDTO} to a {@link DoctorSchedule} entity.
     *
     * @param doctorScheduleDTO the {@link DoctorScheduleDTO} to convert
     * @return the converted {@link DoctorSchedule} entity
     */	
	DoctorSchedule toDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO);
	

}

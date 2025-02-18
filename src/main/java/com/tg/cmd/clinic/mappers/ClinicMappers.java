package com.tg.cmd.clinic.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.tg.cmd.clinic.dto.ClinicDTO;
import com.tg.cmd.clinic.model.Clinic;

import jakarta.annotation.ManagedBean;

@Mapper(componentModel = "spring")
public interface ClinicMappers {
      Clinic toClinic(ClinicDTO clinicDto);
      ClinicDTO toClinicDTO(Clinic clinic);
      
   // Method to map a list of Clinic entities to a list of ClinicDTOs
      default List<ClinicDTO> toClinicDTOList(List<Clinic> clinics) {
          return clinics.stream()
                  .map(this::toClinicDTO) // Map each Clinic to ClinicDTO
                  .collect(Collectors.toList());
      }
      
}

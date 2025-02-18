package com.tg.cmd.clinic.mappers;

import com.tg.cmd.clinic.dto.ClinicDTO;
import com.tg.cmd.clinic.model.Clinic;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-18T18:03:07+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 23 (Eclipse Adoptium)"
)
@Component
public class ClinicMappersImpl implements ClinicMappers {

    @Override
    public Clinic toClinic(ClinicDTO clinicDto) {
        if ( clinicDto == null ) {
            return null;
        }

        Clinic clinic = new Clinic();

        clinic.setEmail( clinicDto.getEmail() );
        clinic.setEndTime( clinicDto.getEndTime() );
        clinic.setId( clinicDto.getId() );
        clinic.setIsServiceActive( clinicDto.getIsServiceActive() );
        clinic.setName( clinicDto.getName() );
        clinic.setPhoneNumber( clinicDto.getPhoneNumber() );
        clinic.setStartTime( clinicDto.getStartTime() );

        return clinic;
    }

    @Override
    public ClinicDTO toClinicDTO(Clinic clinic) {
        if ( clinic == null ) {
            return null;
        }

        ClinicDTO clinicDTO = new ClinicDTO();

        clinicDTO.setEmail( clinic.getEmail() );
        clinicDTO.setEndTime( clinic.getEndTime() );
        clinicDTO.setId( clinic.getId() );
        clinicDTO.setIsServiceActive( clinic.getIsServiceActive() );
        clinicDTO.setName( clinic.getName() );
        clinicDTO.setPhoneNumber( clinic.getPhoneNumber() );
        clinicDTO.setStartTime( clinic.getStartTime() );

        return clinicDTO;
    }
}

package com.tgl.cmd.appointments.mappers;

import com.tgl.cmd.appointments.dto.CreateAppointmentDTO;
import com.tgl.cmd.appointments.model.Appointment;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T13:54:49+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public CreateAppointmentDTO toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        CreateAppointmentDTO createAppointmentDTO = new CreateAppointmentDTO();

        createAppointmentDTO.setDoctor( appointment.getDoctor() );
        createAppointmentDTO.setPatient( appointment.getPatient() );
        if ( appointment.getAppointmentTime() != null ) {
            createAppointmentDTO.setAppointmentTime( DateTimeFormatter.ISO_LOCAL_TIME.format( appointment.getAppointmentTime() ) );
        }
        createAppointmentDTO.setAppointmentStatus( appointment.getAppointmentStatus() );
        createAppointmentDTO.setPurposeOfVisit( appointment.getPurposeOfVisit() );
        createAppointmentDTO.setAppointmentType( appointment.getAppointmentType() );

        createAppointmentDTO.setAppointmentDate( formatDate(appointment.getAppointmentDate()) );
        createAppointmentDTO.setUserId( appointment.getUserId() );


        return createAppointmentDTO;
    }

    @Override
    public Appointment toAppointment(CreateAppointmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        if ( dto.getAppointmentTime() != null ) {
            appointment.setAppointmentTime( LocalTime.parse( dto.getAppointmentTime() ) );
        }
        appointment.setDoctor( dto.getDoctor() );
        appointment.setPatient( dto.getPatient() );
        appointment.setPurposeOfVisit( dto.getPurposeOfVisit() );
        appointment.setAppointmentType( dto.getAppointmentType() );
        appointment.setAppointmentStatus( dto.getAppointmentStatus() );
        appointment.setUserId( dto.getUserId() );
        appointment.setAppointmentDate( parseDate(dto.getAppointmentDate()) );

        return appointment;
    }
}

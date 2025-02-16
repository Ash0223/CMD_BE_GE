package com.tgl.cmd.appointments.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Appointment entity representing an appointment in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    // Appointment ID generated using custom ID generator
    @Id
    @GenericGenerator(name = "appointmentId", strategy = "com.tgl.cmd.appointments.model.IdGenerator")
    @GeneratedValue(generator = "appointmentId")
    @Column
    private String appointmentId;
    
    // Date of the appointment
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private LocalDate appointmentDate;
    
    // Time of the appointment
    @DateTimeFormat(iso = ISO.TIME)
    @Column
    private LocalTime appointmentTime;
    
    @Embedded
    private Doctor doctor;
    
    @Embedded
    private Patient patient;
    
//    @Embedded
//    private Clinic clinic;
//    
    
    // Doctor will update details after consultations
    @Column
    private String doctorReview;
    
    
    // Purpose of visit enum
    @Enumerated(EnumType.STRING)
    @Column
    private Purpose purposeOfVisit;
    
    
    // Purpose of visit enum
    @Enumerated(EnumType.STRING)
    @Column
    private AppointmentType appointmentType;
    
    
    // Appointment status enum
    @Enumerated(EnumType.STRING)
    @Column
    private AppointmentStatus appointmentStatus;
    

    
}
















































//// Date of creation
//@DateTimeFormat(iso = ISO.DATE)
//@Column(updatable = false)
//@CreationTimestamp
//private LocalDateTime createdDate;
//
//// Date of modification
//@DateTimeFormat(iso = ISO.DATE)
//@Column
//private LocalDateTime modifiedDate;
//
//// Created by
//@Column(updatable = false)
//private String createdBy;
//
//// Modified by
//@Column
//private String modifiedBy;
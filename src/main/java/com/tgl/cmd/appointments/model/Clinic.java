package com.tgl.cmd.appointments.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Doctor entity representing a doctor in the system
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Clinic {

    private String clinicId;
    private String clinicName;
    
}

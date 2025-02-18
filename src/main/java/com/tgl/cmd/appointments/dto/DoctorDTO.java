package com.tgl.cmd.appointments.dto;

import lombok.Data;
import java.util.List;

@Data
public class DoctorDTO {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String clinicId;
    private List<ExperienceDTO> experiences;

    @Data
    public static class ExperienceDTO {
        private String clinicName;
    }
}

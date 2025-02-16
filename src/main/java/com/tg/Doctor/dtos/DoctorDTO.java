package com.tg.Doctor.dtos;

import java.time.LocalDate;
import java.util.List;

import com.tg.Doctor.models.Address;
import com.tg.Doctor.models.Experience;
import com.tg.Doctor.models.Gender;
import com.tg.Doctor.models.Qualification;
import com.tg.Doctor.models.Specialization;
import com.tg.Doctor.models.Status;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for Doctor.
 * This class contains the data fields and validation rules for the Doctor entity, 
 * and is used for transferring Doctor-related information between different layers of the application.
 */
@Data
public class DoctorDTO {
 
    /**
     * The first name of the doctor.
     * It must be between 2 and 50 characters long and contain only alphabetic characters.
     */
    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[A-Za-z]{2,50}$", message = "First name must only contain letters and be 2 to 50 characters long")
    private String firstName;

    /** 
     * The last name of the doctor.
     * It must be between 2 and 50 characters long and contain only alphabetic characters.
     */
    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[A-Za-z]{2,50}$", message = "Last name must only contain letters and be 2 to 50 characters long")
    private String lastName;

    /**
     * The phone number of the doctor.
     * It must be exactly 10 digits.
     */
    @NotBlank(message = "Phone number is mandatory")
    private Long phoneNum;

    /**
     * The date of birth of the doctor.
     * It must be a past date.
     */
    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be a past date")
    private LocalDate dateOfBirth;

    /**
     * The email address of the doctor.
     * It must be a valid email format.
     */
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The years of experience the doctor has.
     * The experience must be between 0 and 50 years.
     */
    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 50, message = "Experience cannot exceed 50 years")
    private int experienceInYears;

    
    @Size(max = 250, message = "Specialization description cannot exceed 250 characters")
    @NotNull(message = "Specialization must be specified")
    private Specialization specialization;
    
  
    @NotNull(message = "Must specify the clinic you're associated with")
    private String clinicId;

    /**
     * The address of the doctor.
     * This field cannot be blank.
     */
    @NotBlank(message = "Address is mandatory")
    private Address address;

    /**
     * The gender of the doctor.
     * This field cannot be null.
     */
    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    /**
     * The current status of the doctor.
     * This field cannot be null.
     */
    @NotNull(message = "Status is mandatory")
    private Status status;

    /**
     * A list of experiences of the doctor.
     * This field cannot be null and must contain at least one experience.
     */
    @NotNull(message = "Must specify Experience")
    private List<Experience> experiences;
    
    /** 
     * A list of qualifications of the doctor.
     * This field cannot be null and must contain at least one qualification.
     */
    @NotNull(message = "Have to specify qualification")
    private List<Qualification> qualifications; 
    
   

}

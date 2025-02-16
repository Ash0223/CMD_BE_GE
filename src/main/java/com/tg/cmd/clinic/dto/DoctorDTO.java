package com.tg.cmd.clinic.dto;

import java.time.LocalDate;
import java.util.List;

import com.tg.cmd.clinic.model.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
* Data Transfer Object (DTO) class for Doctor.
* This class contains the data fields and validation rules for the Doctor entity, 
* and is used for transferring Doctor-related information between different layers of the application.
*/
//@XmlRootElement(name = "Doctor")
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
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNum;
 
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
 
    /**
     * The specialization type of the doctor.
     * This field cannot be blank.
     */
    @NotBlank(message = "Specialization type is mandatory")
    private String specializationType;
 
    /**
     * A description of the doctor's specialization.
     * The description cannot exceed 250 characters.
     */
    @Size(max = 250, message = "Specialization description cannot exceed 250 characters")
    private String specializationDescription;
 
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getExperienceInYears() {
		return experienceInYears;
	}

	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
	}

	public String getSpecializationType() {
		return specializationType;
	}

	public void setSpecializationType(String specializationType) {
		this.specializationType = specializationType;
	}

	public String getSpecializationDescription() {
		return specializationDescription;
	}

	public void setSpecializationDescription(String specializationDescription) {
		this.specializationDescription = specializationDescription;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}


 
}
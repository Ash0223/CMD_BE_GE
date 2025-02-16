package com.tg.Doctor.models;

import java.time.LocalDate; // Importing LocalDate for handling date of birth
import java.util.List; // Importing List for handling collections

import org.hibernate.annotations.GenericGenerator; // Importing GenericGenerator for generating unique IDs
import jakarta.persistence.Column; // Importing Column for mapping entity columns
import jakarta.persistence.ElementCollection; // Importing ElementCollection for mapping collections of basic types or embeddable classes
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity; // Importing Entity to define a JPA entity
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue; // Importing GeneratedValue for specifying the generation strategy of primary keys
import jakarta.persistence.Id; // Importing Id for specifying the primary key of an entity
import jakarta.persistence.Table; // Importing Table to specify the table name for an entity
import lombok.AllArgsConstructor; // Importing AllArgsConstructor for generating an all-arguments constructor
import lombok.Data; // Importing Data for generating getters, setters, equals, hashCode, and toString
import lombok.NoArgsConstructor; // Importing NoArgsConstructor for generating a no-arguments constructor

/**
 * Represents a doctor entity in the system with personal, professional, and contact information.
 * The Doctor entity is mapped to the "Doctor" table in the database.
 */
@Entity // Specifies that the class is an entity
@AllArgsConstructor // Generates an all-arguments constructor
@Data // Generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // Generates a no-arguments constructor
@Table(name = "Doctor") // Specifies the table name for the entity
public class Doctor {

    /**
     * Unique identifier for a doctor.
     * This is the primary key of the Doctor entity and is auto-generated.
     */
    @Id // Specifies the primary key of the entity
    @GenericGenerator(name = "doctor_Id", strategy = "com.tg.Doctor.models.IdGenerator") // Specifies the generation strategy for the primary key
    @GeneratedValue(generator = "doctor_Id") // Generates the value for the primary key
    @Column(name = "Doctor_Id") // Specifies the column name for the primary key
    private String doctorId; // Represents the unique identifier for a doctor

    /** 
     * First name of the doctor.
     */
    @Column(name = "First_Name") 
    private String firstName; 
  
    /**
     * Last name of the doctor.
     */
    @Column(name = "Last_Name") 
    private String lastName;

    /**
     * Phone number of the doctor.
     */
    @Column(name = "Phone_Num") 
    private Long phoneNum;

    /**
     * Date of birth of the doctor.
     */
    @Column(name = "Date_Of_Birth") // Specifies the column name for the date of birth
    private LocalDate dateOfBirth; // Represents the date of birth of the doctor

    /**
     * Email address of the doctor.
     */
    @Column(name = "Email") // Specifies the column name for the email
    private String email; // Represents the email address of the doctor

    /**
     * Years of experience the doctor has in the medical field.
     */
    @Column(name = "Experience_In_Years") 
    private int experienceInYears;

    /**
     * Gender of the doctor.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Gender") // Specifies the column name for the gender
    private Gender gender; // Represents the gender of the doctor

    /**
     * Status of the doctor (e.g., active, inactive).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "Status") // Specifies the column name for the status
    private Status status; // Represents the status of the doctor

    /**
     * List of clinics where the doctor practices.
     */
    @Column(name = "clinicId")
    private String clinicId;

    /**
     * Address of the doctor.
     */
    @Embedded
    private Address address;
 
    /**
     * Specialization of the doctor (e.g., cardiology, orthopedics).
     */
    @Embedded
    private Specialization specialization;

    /**
     * List of experiences the doctor has in the medical field.
     */
    @ElementCollection
    private List<Experience> experiences;

    /**
     * List of qualifications the doctor has attained.
     */
    @ElementCollection
    private List<Qualification> qualifications;

}
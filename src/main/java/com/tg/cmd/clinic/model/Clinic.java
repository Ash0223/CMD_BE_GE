package com.tg.cmd.clinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clinics")
public class Clinic {

    

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private String id;
	@Id
    @GenericGenerator(name = "clinicId", strategy = "com.tg.cmd.clinic.model.IdGenerator")
    @GeneratedValue(generator = "clinicId")
	@Column(name = "id")
	private String id;

    @Column(name = "Phone_number")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Is_service_active")
    private boolean isServiceActive;
    
    
    @Column(name = "Start_Time")
    private LocalDateTime startTime;

    @Column(name = "End_Time")
    private LocalDateTime endTime;

    @Column(name = "Status")
    private boolean status ;

    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Embedded
    private Address address;
    
    
    @ElementCollection
    @CollectionTable(name = "clinic_doctors", joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "doctor_id")
    private List<String> doctorIds;
  
    @ElementCollection
    @CollectionTable(name = "clinic_pictures", joinColumns = @JoinColumn(name = "clinic_id"))
    private List<Picture> pictures;
    
    
    
    public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<String> getDoctorIds() {
		return doctorIds;
	}

	public void setDoctorIds(List<String> doctorIds) {
		this.doctorIds = doctorIds;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
   public void setIsServiceActive(boolean b) {
		
		this.isServiceActive=b;
	}
    
	public boolean getIsServiceActive() {
	 return this.isServiceActive;	
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

//	public boolean isServiceActive() {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
}








package com.tg.cmd.clinic.dto;



import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ClinicDTO {
    private String id;
    private String phoneNumber;
    private String email;
    private String name;
    private boolean isServiceActive;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public boolean isServiceActive() {
//		return isServiceActive;
//	}
	public boolean getIsServiceActive(){
		return this.isServiceActive;
	}
	
	public void setIsServiceActive(boolean isServiceActive) {
		this.isServiceActive = isServiceActive;
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
	
	

    
}

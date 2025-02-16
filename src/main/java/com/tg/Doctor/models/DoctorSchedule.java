package com.tg.Doctor.models;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "Doctor_Schedule")
public class DoctorSchedule {
	
		@Id
		@Column(name = "Doctor_Id")
		@JoinColumn(name = "doctor_Id")
		private String doctorId;

		// Name associated with the appointment
		@Column(name = "Name")
		private String Name;
		
		// Day of the appointment
		@Column(name = "Week_Day")
		@Enumerated(EnumType.STRING)
	    private Days weekDay;
		
		// Start time of the appointment
		@Column(name = "start_time")
		private String startTime;
		    
		// End time of the appointment
		@Column(name = "end_time")
		private String endTime;
		
	    @Enumerated(EnumType.STRING)
	    @Column(name = "Availability_Mode")
	    private AvailabilityMode availabilityMode;
	    
	    
	    private String clinicId;

		

}

package com.tg.Doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tg.Doctor.models.JwtTokenValidatorFilter;


@SpringBootApplication
public class DoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	} 
	
	 @Bean
	 public JwtTokenValidatorFilter jwtTokenValidatorFilter() {
	    return new JwtTokenValidatorFilter();
	    }

}

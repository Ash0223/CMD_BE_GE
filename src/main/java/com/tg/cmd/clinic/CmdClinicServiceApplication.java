package com.tg.cmd.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CmdClinicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmdClinicServiceApplication.class, args);
		
		
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		//RestTemplate restTemplate = new RestTemplate();
	    //restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
		return new RestTemplate();
	}

}

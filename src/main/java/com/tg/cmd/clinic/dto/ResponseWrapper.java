package com.tg.cmd.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
	
	private String message;
	private T object;
	public ResponseWrapper(T object) {
		super();
		this.object = object;
	}
	public ResponseWrapper(String message) {
		super();
		this.message = message;
	}
	
	

}

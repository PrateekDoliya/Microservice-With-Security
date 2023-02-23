package com.hotel.service.payloads;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

	private String message;
	private Boolean success;
	private HttpStatus status;
	

}

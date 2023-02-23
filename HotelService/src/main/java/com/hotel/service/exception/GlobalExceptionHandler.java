package com.hotel.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.service.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handelResourceNotFoundException(ResourceNotFoundException exception) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(exception.getMessage(), false, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
	
}

package com.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.service.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handelResourceNotFoundException(ResourceNotFoundException exception) {
		String msg = exception.getMessage();
		return new ResponseEntity<ApiResponse>(new ApiResponse(msg, false, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	}
	
}

package com.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String fieldName;
	private String fieldValue;
	
	
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super("Resource Not Found on server !!");	
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s Not Found With %s : %s",resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
	
}

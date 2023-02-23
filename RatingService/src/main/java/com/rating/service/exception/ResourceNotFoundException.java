package com.rating.service.exception;

public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String fieldName;
	private String fieldValue;
	
	public ResourceNotFoundException() {
		super("Resource Not Found On Server!!");
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s Not Found With %s : %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
}

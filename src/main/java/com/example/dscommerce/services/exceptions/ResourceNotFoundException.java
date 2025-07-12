package com.example.dscommerce.services.exceptions;

//RuntimeException não exige try catch
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}

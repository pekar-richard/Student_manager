package com.example.demo.exceptions;

public class AgenturNotFoundExceptionResponse {
	
	private String AgenturNotFound;

	public AgenturNotFoundExceptionResponse(String agenturNotFound) {
		AgenturNotFound = agenturNotFound;
	}

	public String getAgenturNotFound() {
		return AgenturNotFound;
	}

	public void setAgenturNotFound(String agenturNotFound) {
		AgenturNotFound = agenturNotFound;
	}
	

}

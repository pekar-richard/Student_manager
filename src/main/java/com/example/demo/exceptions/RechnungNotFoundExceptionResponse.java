package com.example.demo.exceptions;

public class RechnungNotFoundExceptionResponse {
	
	private String RechnungNotFound;

	public String getRechnungNotFound() {
		return RechnungNotFound;
	}

	public void setRechnungNotFound(String rechnungNotFound) {
		RechnungNotFound = rechnungNotFound;
	}

	public RechnungNotFoundExceptionResponse(String rechnungNotFound) {
		RechnungNotFound = rechnungNotFound;
	}

}

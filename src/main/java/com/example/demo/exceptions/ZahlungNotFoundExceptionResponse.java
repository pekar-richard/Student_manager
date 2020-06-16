package com.example.demo.exceptions;

public class ZahlungNotFoundExceptionResponse {
	
	private String ZahlungNotFound;

	public String getZahlungNotFound() {
		return ZahlungNotFound;
	}

	public void setZahlungNotFound(String zahlungNotFound) {
		ZahlungNotFound = zahlungNotFound;
	}

	public ZahlungNotFoundExceptionResponse(String zahlungNotFound) {
		ZahlungNotFound = zahlungNotFound;
	}
	
}

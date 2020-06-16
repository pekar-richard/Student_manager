package com.example.demo.exceptions;


public class LektionNotFoundExceptionResponse{
	
	private String LektionNotFound;

	public String getLektionNotFound() {
		return LektionNotFound;
	}

	public void setLektionNotFound(String lektionNotFound) {
		LektionNotFound = lektionNotFound;
	}

	public LektionNotFoundExceptionResponse(String lektionNotFound) {
		LektionNotFound = lektionNotFound;
	}
	

}

package com.example.demo.exceptions;

public class StudentNotFoundExceptionResponse {
	
	private String StudentNotFound;
	

	public StudentNotFoundExceptionResponse(String studentNotFound) {
		StudentNotFound = studentNotFound;
	}

	public String getStudentNotFound() {
		return StudentNotFound;
	}

	public void setStudentNotFound(String studentNotFound) {
		StudentNotFound = studentNotFound;
	}

	
}

package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomReponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleStudentException(StudentNotFoundException ex, WebRequest request ){
		
		StudentNotFoundExceptionResponse exceptionResponse = new StudentNotFoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleAgenturException(AgenturNotFoundException ex, WebRequest request ){
		
		AgenturNotFoundExceptionResponse exceptionResponse = new AgenturNotFoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleLektionException(LektionNotFoundException ex, WebRequest request ){
		
		LektionNotFoundExceptionResponse exceptionResponse = new LektionNotFoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleRechnungException(RechnungNotFoundException ex, WebRequest request ){
		
		RechnungNotFoundExceptionResponse exceptionResponse = new RechnungNotFoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleZahlungException(ZahlungNotFoundException ex, WebRequest request ){
		
		ZahlungNotFoundExceptionResponse exceptionResponse = new ZahlungNotFoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
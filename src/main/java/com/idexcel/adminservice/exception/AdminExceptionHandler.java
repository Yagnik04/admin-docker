package com.idexcel.adminservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdminExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<AdminErrorResponse> handleException(AdminNotFoundException exc){
	
		AdminErrorResponse error = new AdminErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<AdminErrorResponse> handleException(AdminAlreadyExistException exc){
		AdminErrorResponse error = new AdminErrorResponse();
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.CONFLICT);
	}
	
	public ResponseEntity<AdminErrorResponse> handleException(NoContentException exc){
		AdminErrorResponse error = new AdminErrorResponse();
		error.setStatus(HttpStatus.NO_CONTENT.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NO_CONTENT);
		
	}
	

	public ResponseEntity<AdminErrorResponse> handleException(IdConflictException exc){
		AdminErrorResponse error = new AdminErrorResponse();
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<AdminErrorResponse> handleException(Exception exc){
		AdminErrorResponse error = new AdminErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
}

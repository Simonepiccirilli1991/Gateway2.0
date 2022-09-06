package com.gwsm0.rest.error;

import java.util.Map;

import org.springframework.http.HttpStatus;



public class BaseActionException extends RuntimeException{

	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
	// TODO finire implementare


		

	// DTO stupido creato per poter passare tre valori da costruttore a costruttore

}
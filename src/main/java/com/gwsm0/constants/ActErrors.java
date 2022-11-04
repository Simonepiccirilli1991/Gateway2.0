package com.gwsm0.constants;

import org.springframework.http.HttpStatus;

import com.gwsm0.rest.error.BaseActionException;

public enum ActErrors {

	
	INVALIDACTION ("Action not validated",    HttpStatus.UNAUTHORIZED),
	DEFAULTACTION ("" , "Error . da definire"),
	INVALIDREQUEST ("Parameters missing",    HttpStatus.BAD_REQUEST);
	
	
	private final String message;
    private final HttpStatus status;
    
	ActErrors(String message, String code) {
        this.message = message;
        this.status = HttpStatus.FORBIDDEN; // Default value
    }
    ActErrors(String message, HttpStatus status) {
        this.message = message;
		
        this.status = status;
    }
    public String getMessage() { return  " [" + this.message + ']'; }
    public String getCode() { return "" ; }
    public HttpStatus getStatus() { return this.status; }
}

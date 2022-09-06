package com.gwsm0.constants;

import org.springframework.http.HttpStatus;

import com.gwsm0.rest.error.BaseActionException;

public enum ActErrors {

	
	INVALIDACTION ("Action not validated",                                "invalidaction",    HttpStatus.UNAUTHORIZED),
	DEFAULTACTION ("" , "Error . da definire"),
	INVALIDREQUEST ("Parameters missing",                                  "invalidrequest",   HttpStatus.BAD_REQUEST);
	
	
	private final String message;
    private final String code;
    private final HttpStatus status;
    
	ActErrors(String message, String code) {
        this.message = message;
        this.code = code;
        this.status = HttpStatus.FORBIDDEN; // Default value
    }
    ActErrors(String message, String code, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
    public String getMessage() { return this.code + " [" + this.message + ']'; }
    public String getCode() { return "" + this.code; }
    public HttpStatus getStatus() { return this.status; }
}

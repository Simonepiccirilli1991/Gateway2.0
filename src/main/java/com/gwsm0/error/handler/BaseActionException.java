package com.gwsm0.error.handler;

import org.springframework.http.HttpStatus;

import com.gwsm0.constants.ActErrors;


public class BaseActionException extends RuntimeException {

	public static final String LOGIC_PREFIX = "gwsm0.logic.";
	private static final String GENERIC = "gwsm0.generic";
	private static final long serialVersionUID = 1L;
	private String errId;
	
	public String getErrId() { return errId; }
	public void setErrId(String errId) { this.errId = errId; }
	
	public BaseActionException(String code, HttpStatus responseStatus) { super(code); }
	
	public BaseActionException(String code) { super(code); }
	
	
	public BaseActionException(String code, String message) {
		this(message);
		this.setErrId(code);	// Mi segno l'errore di input in modo da poterci fare alcune logiche esternamente (ad esempio in dei catch)
	}
	public BaseActionException(ActErrors invalidrequest) {}
	
}

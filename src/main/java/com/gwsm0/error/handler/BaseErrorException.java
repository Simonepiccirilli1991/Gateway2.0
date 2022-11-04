package com.gwsm0.error.handler;

import org.springframework.http.HttpStatus;

public class BaseErrorException extends RuntimeException{

	private HttpStatus status = null;

	private Object data = null;

	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BaseErrorException() {
		super();
	}

	public BaseErrorException(String message) { 
		super(message);
	}

	public BaseErrorException(HttpStatus status,String message) {
		this(message);
		this.status = status;
	}

	public BaseErrorException(HttpStatus status,String message,Object data) {
		this(status,message);
		this.data = data;
	}
}


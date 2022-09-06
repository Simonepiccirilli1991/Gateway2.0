package com.gwsm0.rest.error;

import org.springframework.http.HttpStatus;

public class BaseError {

	private String errorMsg;
	private String errorTp;
	private String chiamante;
	private HttpStatus httpS;
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorTp() {
		return errorTp;
	}
	public void setErrorTp(String errorTp) {
		this.errorTp = errorTp;
	}
	public String getChiamante() {
		return chiamante;
	}
	public void setChiamante(String chiamante) {
		this.chiamante = chiamante;
	}
	public HttpStatus getHttpS() {
		return httpS;
	}
	public void setHttpS(HttpStatus httpS) {
		this.httpS = httpS;
	}
	
	
}

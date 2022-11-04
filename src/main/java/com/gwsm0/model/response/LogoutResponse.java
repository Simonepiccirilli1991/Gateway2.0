package com.gwsm0.model.response;

public class LogoutResponse {

	private boolean logout;
	private String message;
	
	public boolean isLogout() {
		return logout;
	}
	public void setLogout(boolean logout) {
		this.logout = logout;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionRequest;

import lombok.Data;

@Data
public class PinRequest extends BaseActionRequest{

	private String bt;
	private String pin;
	private String username;
	private String email;
	private String cf;
	private String otp;
	private String newPin;
	
	// getter e setter perche eclipse di merda su mac non scarica lombock
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getNewPin() {
		return newPin;
	}
	public void setNewPin(String newPin) {
		this.newPin = newPin;
	}
	
	
	
	
	
}

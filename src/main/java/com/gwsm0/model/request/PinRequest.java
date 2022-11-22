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
	
	
	
	
	
}

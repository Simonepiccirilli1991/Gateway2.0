package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionRequest;

import lombok.Data;

@Data
public class LoginRequest extends BaseActionRequest{
	
	private String bt;
	private String abi;
	private String pin;
	private String otp;
	private String oldPin;
	private Boolean ricordami;
	private String ricordamiToken;
	
	

}

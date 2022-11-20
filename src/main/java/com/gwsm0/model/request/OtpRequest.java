package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionRequest;

import lombok.Data;

@Data
public class OtpRequest extends BaseActionRequest{

	private String ots;
	private String email;
	private String bt;
	
	
}

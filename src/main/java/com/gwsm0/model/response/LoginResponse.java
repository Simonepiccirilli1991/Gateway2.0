package com.gwsm0.model.response;

import com.gwsm0.model.base.BaseActionResponse;

import lombok.Data;

@Data
public class LoginResponse extends BaseActionResponse{

	private String msg;
	private Boolean dataRichiesta;
	
}

package com.gwsm0.command;

import org.springframework.http.HttpHeaders;

import com.gwsm0.model.request.LoginRequest;
import com.gwsm0.model.response.LoginResponse;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.service.CheckPinService;

public class LoginCommand extends BaseActionCommand<LoginRequest,LoginResponse>{

	public LoginCommand(LoginRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
		// TODO Auto-generated constructor stub
	}
	
	public LoginResponse doExecute() throws Exception {
		switch (super.iRequest.getAction()) {
		// normale checkpin
			case CHECKPIN:	
		//cambio pin
			case CAMBIOPIN:	
				
			case RECUPERAPIN:
	
		default:
			return super.throwDefault();
		}
	}

}

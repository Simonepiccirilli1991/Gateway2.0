package com.gwsm0.command;

import org.springframework.http.HttpHeaders;

import com.gwsm0.model.base.BaseActionResponse;
import com.gwsm0.model.request.UtenteRequest;
import com.gwsm0.rest.service.RegistraUtenteService;

public class UtenteCommand extends BaseActionCommand<UtenteRequest, BaseActionResponse>{

	public UtenteCommand(UtenteRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
		
	}

	
	public BaseActionResponse doExcute() throws Exception{
		switch (super.iRequest.getAction()) {
		// chiama alla status di wiam
			case REGISTRATI:
				return super.getResponse(RegistraUtenteService.class);
		default:
			return super.throwDefault();
		}
	
}
	
}

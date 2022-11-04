package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.service.CheckPinService;

@Component
@Scope("prototype")
public class PinCommand extends BaseActionCommand<PinRequest, PinResponse>{

	public PinCommand(PinRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
	}

	
	public PinResponse doExecute() throws Exception {
		switch (super.iRequest.getAction()) {
		// normale checkpin
			case CHECKPIN:
				return super.getResponse(CheckPinService.class);
		//check utente esiste - usata per vedere se username gia in uso
			case CHECKUTENTE:	
				
		//creazione pin
			case CREAZIONEPIN:
		//cambio pin
			case CAMBIOPIN:	
	
		default:
			return super.throwDefault();
		}
	}
}

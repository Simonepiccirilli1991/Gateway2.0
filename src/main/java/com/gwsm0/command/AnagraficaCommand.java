package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.gwsm0.model.request.AnagraficaRequest;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.rest.service.AnagraficaAddService;
import com.gwsm0.rest.service.AnagraficaModService;

@Component
@Scope("prototype")
public class AnagraficaCommand extends BaseActionCommand<AnagraficaRequest, AnagraficaResponse>{

	public AnagraficaCommand(AnagraficaRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
	}

	
	public AnagraficaResponse doExecute() throws Exception {
		switch (super.iRequest.getAction()) {
		
			case ANAGRAFICAADD:
				return super.getResponse(AnagraficaAddService.class);
		// gestita all'interno dell'add su logica action
			case ANAGRAFICAGET:	
				return super.getResponse(AnagraficaAddService.class);
			case ANAGRAFICAMOD:	
				return super.getResponse(AnagraficaModService.class);
		default:
			return super.throwDefault();
		}
	}
}

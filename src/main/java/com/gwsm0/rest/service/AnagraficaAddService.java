package com.gwsm0.rest.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.AnagraficaRequest;
import com.gwsm0.model.response.AnagraficaResponse;

@Service
public class AnagraficaAddService extends BaseActionService<AnagraficaRequest, AnagraficaResponse>{

	@Override
	public AnagraficaResponse call_(AnagraficaRequest iRequest, HttpHeaders httpHeaders) {
		
		AnagraficaResponse response = new AnagraficaResponse();
		
		// controllo se action è get anagrafica
		if(iRequest.getAction().equals(ActionConstants.ANAGRAFICAGET)) {
			// chiamata alla get di wiam
			
			return response;
		}
			
		
		
		
		return null;
	}

}

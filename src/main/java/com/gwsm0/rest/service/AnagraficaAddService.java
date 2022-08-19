package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.AnagraficaRequest;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.rest.fragment.wiam.AnagraficaWiam;

@Service
public class AnagraficaAddService extends BaseActionService<AnagraficaRequest, AnagraficaResponse>{

	@Autowired
	AnagraficaWiam anagWiam;
	
	@Override
	public AnagraficaResponse call_(AnagraficaRequest iRequest, HttpHeaders httpHeaders) {
		
		AnagraficaResponse response = new AnagraficaResponse();
		
		// controllo se action è get anagrafica
		if(iRequest.getAction().equals(ActionConstants.ANAGRAFICAGET)) {
			response = anagWiam.getAnagrafica(httpHeaders.getFirst("USERNAME"));
			
			return response;
		}
			
		AnagraficaWRequest requestW = new AnagraficaWRequest();
		requestW = iRequest.getAnagrafica();
		
		response.setAnagrafica(anagWiam.addAnagrafica(requestW).getAnagrafica());
		
		
		return response;
	}

}

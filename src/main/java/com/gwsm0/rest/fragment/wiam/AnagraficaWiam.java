package com.gwsm0.rest.fragment.wiam;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.fragment.model.wiam.AnagraficaWResponse;
import com.gwsm0.model.response.AnagraficaResponse;

@Service
public class AnagraficaWiam {

	
	RestTemplate restService = new RestTemplate();
	// servizio per la get
	public AnagraficaResponse getAnagrafica(String username) {
		AnagraficaWResponse response = new AnagraficaWResponse();
		AnagraficaResponse oResponse = new AnagraficaResponse();
		String fooResourceUrl
		= "http://localhost:8083/wiam/get/anagrafica/";
		
		try {
			response = restService.getForObject(fooResourceUrl+username, AnagraficaWResponse.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new BaseActionException("GWSM0-GENERIC-ERROR", HttpStatus.SERVICE_UNAVAILABLE);
			
		}
		if(response.getIsError())
			throw new BaseActionException(response.getCodiceEsito(), HttpStatus.valueOf(500));
		
		oResponse.setAnagrafica(response.getAnagrafica());
		
		return oResponse;
	}
	
	
	// servizio per l'add
	
	public AnagraficaWResponse addAnagrafica(AnagraficaWRequest request) {
		AnagraficaWResponse response = new AnagraficaWResponse();
		
		String fooResourceUrl
		= "http://localhost:8083/wiam/add/anagrafica";
		
		try {
			response = restService.postForObject(fooResourceUrl, request, AnagraficaWResponse.class);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new BaseActionException("GWSM0-GENERIC-ERROR", HttpStatus.SERVICE_UNAVAILABLE);
			
		}
		
		return response;
	}
}

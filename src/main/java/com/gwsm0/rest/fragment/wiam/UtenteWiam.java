package com.gwsm0.rest.fragment.wiam;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.fragment.model.wiam.BaseWiamResponse;
import com.gwsm0.model.request.UtenteRequest;


@Service
public class UtenteWiam {

	RestTemplate restService = new RestTemplate();
	
	// salva utente WIAM
	public BaseWiamResponse salvaUtente(UtenteRequest request) {
		BaseWiamResponse response = new BaseWiamResponse();
		
		String fooResourceUrl
		= "http://localhost:8083/wiam/salva/utente";
		
		 response = restService.postForObject(fooResourceUrl , request, BaseWiamResponse.class);
		 
		 return response;
	}
}

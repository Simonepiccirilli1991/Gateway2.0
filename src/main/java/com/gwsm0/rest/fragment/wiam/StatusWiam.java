package com.gwsm0.rest.fragment.wiam;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;


@Service
public class StatusWiam {

	
	RestTemplate restService = new RestTemplate();
	
	public StatusResponse  statusW(StatusRequest request) {
		
		StatusResponse response = new StatusResponse();
		try {
			String fooResourceUrl
			= "http://localhost:8081/wiam/status";
		 response = restService.postForObject(fooResourceUrl , request, StatusResponse.class);
		 //TODO aggiungere action exception e mappatura errore
		}catch(Exception e) {
			
		}
		
		return response;
	}
	
	
	
	
}

package com.gwsm0.rest.fragment.wiam;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.fragment.model.wiam.StatusWResponse;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;


@Service
public class StatusWiam {

	
	RestTemplate restService = new RestTemplate();
	
	public StatusResponse  statusW(StatusRequest request) {
		
		StatusResponse response = new StatusResponse();
		String fooResourceUrl
		= "http://localhost:8081/wiam/status";
		try {
		 response = restService.postForObject(fooResourceUrl , request, StatusResponse.class);
		 //TODO aggiungere action exception e mappatura errore
		}catch(Exception e) {
			
		}
		
		return response;
	}
	
	public StatusWResponse checkStatus(StatusRequest request) {
		
		StatusWResponse response = new StatusWResponse();
		String fooResourceUrl = "http://localhost:8083/wiam/status";
		response = restService.postForObject(fooResourceUrl , request, StatusWResponse.class);
		
		return response;
	}
	
	
}

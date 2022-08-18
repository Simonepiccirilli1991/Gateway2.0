package com.gwsm0.rest.fragment.wiam;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.fragment.model.wiam.EnforcementWRequest;
import com.gwsm0.fragment.model.wiam.EnforcementWResponse;

@Service
public class EnforcementWiam {

	RestTemplate restService = new RestTemplate();
	
	public EnforcementWResponse enforcement(EnforcementWRequest request) {
		
		EnforcementWResponse response = new EnforcementWResponse();
		
		String fooResourceUrl
		= "http://localhost:8083/wiam/enforcement";
		
		response = restService.postForObject(fooResourceUrl, request, EnforcementWResponse.class);
		
		//TODO implementare check su isError e tornare errore
		// implementare il check sull'errore
		return response;
	}
}

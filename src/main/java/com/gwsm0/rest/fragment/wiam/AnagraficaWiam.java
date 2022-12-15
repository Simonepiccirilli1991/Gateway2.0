package com.gwsm0.rest.fragment.wiam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.fragment.model.wiam.AnagraficaWResponse;
import com.gwsm0.model.response.AnagraficaResponse;

@Service
public class AnagraficaWiam {
	
	@Value("${configuration.wiam.getAllAnagraficaWiam}")
	private String getAllAnagEndPoint;
	
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
	
	// get all by cf o nome
	public AnagraficaWResponse getAllAnagbyNameoCf(String tiporicerca, String valore) {
		
		ResponseEntity<AnagraficaWResponse> response = null;
		try {
			response = restService.getForEntity(getAllAnagEndPoint, AnagraficaWResponse.class);
		}catch(Exception e) {
			throw new BaseActionException("Error on connecting wiam, error:" +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(Boolean.TRUE.equals(response.getBody().getIsError()))
			throw new BaseActionException("Error on connecting wiam, error:"+ response.getBody().getErrDsc(), HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response.getBody();
	}
}

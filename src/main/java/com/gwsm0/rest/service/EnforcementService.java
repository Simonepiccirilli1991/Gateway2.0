package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwsm0.fragment.model.wiam.EnforcementWRequest;
import com.gwsm0.fragment.model.wiam.EnforcementWResponse;
import com.gwsm0.model.request.EnforcementRequest;
import com.gwsm0.model.response.EnforcementResponse;
import com.gwsm0.rest.fragment.wiam.EnforcementWiam;

@Service
public class EnforcementService {

	@Autowired
	EnforcementWiam enforcementWiam;
	
	public EnforcementResponse enforcement(EnforcementRequest request) {
		EnforcementResponse response = new EnforcementResponse();
		Boolean ricordami = request.isRicordami();
		String tipoSicurezza = "OTP";
		
		// prima checko otp;
		//TODO implementare chiamara a nuovo servizio(authminder , da creare) deve gestire otp con cache tempo max 1.30
		
		// chiamata ad authminder
		
		
		// se ricordami attivo cambio tipo sicurezza inviato in modo di salvare seed ricordami
		if(ricordami)
			tipoSicurezza = "OTP"+"-"+"ricordami";
		
		// setto chiamata a wiam
		EnforcementWRequest iRequest = new EnforcementWRequest();
		iRequest.setEnforced(true);
		iRequest.setUsername(request.getUsername());
		iRequest.setTipoSicurezza(tipoSicurezza);
		iRequest.setAbiSottoscrizione(request.getAbiSottoscrizione());
		
		// chiamo wiam
		// TODO al momento non torna token ne seed, da implementare su wiam o creare altre 2 api
		EnforcementWResponse oResponse = new EnforcementWResponse();
		oResponse = enforcementWiam.enforcement(iRequest);
		
		response.setTokenRicordami(oResponse.getTokenRicordami());
		response.setSeedEnforcement(oResponse.getSeed());
		response.setMsgResponse("ENFORCEMENT AVVENUTO CON SUCCESSO");
		
		return response;
	}
}

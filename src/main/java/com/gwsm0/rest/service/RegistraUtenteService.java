package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.wiam.BaseWiamResponse;
import com.gwsm0.model.base.BaseActionResponse;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.UtenteRequest;
import com.gwsm0.rest.fragment.wiam.UtenteWiam;

@Service
public class RegistraUtenteService extends BaseActionService<UtenteRequest, BaseActionResponse>{

	@Autowired
	UtenteWiam utenteWiam;
	// salva utente

	@Override
	public BaseActionResponse call_(UtenteRequest iRequest, HttpHeaders httpHeaders) {
		BaseWiamResponse response = new BaseWiamResponse();
		BaseActionResponse oResponse = new BaseActionResponse();
		
		response = utenteWiam.salvaUtente(iRequest);
		
		if(response.getIsError()) {
			throw new BaseActionException(response.getCodiceEsito(), response.getErrDsc());
		}
		oResponse.setAction(ActionConstants.ANAGRAFICAADD);
		
		return oResponse;
	}
	
}

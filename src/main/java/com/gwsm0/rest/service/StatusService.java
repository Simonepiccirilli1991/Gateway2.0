package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.fragment.wiam.StatusWiam;

@Service
public class StatusService extends BaseActionService<StatusRequest, StatusResponse>{

	@Autowired private StatusWiam statusW;
	
	@Override
	public StatusResponse call_(StatusRequest iRequest, HttpHeaders httpHeaders) {
		
		// chiamata a status wiam
		StatusResponse response = statusW.statusW(iRequest);
		
		// se utente non registarto action registrati
		if(!response.isUtenteRegistrato()) {
			response.setAction(ActionConstants.REGISTRATI);
			//response.getSessionData().getSession().setActionId(ActionConstants.REGISTRATI.getId());
			return response;
		}
		// se utente non ha anagrafica
		else if (!response.isAnagragicaP()) {
			response.setAction(ActionConstants.ANAGRAFICA);
			response.getSessionData().getSession().setActionId(ActionConstants.ANAGRAFICA.getId());
			return response;
			}
		// se utente non ha sicurezza censita
		else if(!response.isSicuriro()) {
			response.setAction(ActionConstants.SICUREZZA);
			response.getSessionData().getSession().setActionId(ActionConstants.SICUREZZA.getId());
			return response;
		}
		else {
			if(response.isEnforced()) {
				response.setAction(ActionConstants.CONSENT);
				return response;
			}else {
				response.setAction(ActionConstants.ENFORCEMENT);
				response.getSessionData().getSession().setActionId(ActionConstants.ENFORCEMENT.getId());
				return response;
			}
		}
		
	}
}

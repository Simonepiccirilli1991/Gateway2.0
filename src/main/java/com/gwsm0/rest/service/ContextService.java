package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.response.ContextResponse;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;

@Service
public class ContextService {

	@Autowired
	SecuretySessionFrag secSession;
	
	public ResponseEntity<ContextResponse> contextCreate(ContextRequest request){
		
		//
		String bt = request.getBt();
		String username = request.getUsername();
		String abi = request.getAbi();
		
		SessionSecRequest secSessReqiest = new SessionSecRequest();
		secSessReqiest.setBt(bt);
		secSessReqiest.setUsername(username);
		
		// chiamo session handler sicurezza, controllo che sessione esista e sia in stato l2.
		SessionSecResponse sicSesDTO = secSession.getSessionSec(secSessReqiest);
		//TODO finire di implementare logica
		
		
	}
}

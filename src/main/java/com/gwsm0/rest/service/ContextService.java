package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.session.AppSessionRequest;
import com.gwsm0.fragment.model.session.AppSessionResponse;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.response.ContextResponse;
import com.gwsm0.rest.fragment.session.AppSessionFrag;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;

@Service
public class ContextService {

	@Autowired
	SecuretySessionFrag secSession;
	@Autowired
	AppSessionFrag appSession;
	
	public ResponseEntity<ContextResponse> contextCreate(ContextRequest request) {
		
		//
		ContextResponse response = new ContextResponse();
		String bt = request.getBt();
		String username = request.getUsername();
		String abi = request.getAbi();
		
		SessionSecRequest secSessReqiest = new SessionSecRequest();
		secSessReqiest.setBt(bt);
		secSessReqiest.setUsername(username);
		
		// chiamo session handler sicurezza, controllo che sessione esista e sia in stato l2.
		SessionSecResponse sicSesDTO = secSession.getSessionSec(secSessReqiest);
		// se torna che sessione non esiste o non attiva lancio eccezione
		if(Boolean.TRUE.equals(sicSesDTO.getSessionNoExist()) || Boolean.FALSE.equals(sicSesDTO.isSessionActive())) {
			throw new BaseActionException("GWSM0-SESSION-ERROR", HttpStatus.FORBIDDEN);
		}
		// controllo che lo scope di sessione sia l2 se non lo è eccezione
		if(ObjectUtils.isEmpty(sicSesDTO.getScope()) || !sicSesDTO.getScope().equals("L2")) {
			throw new BaseActionException("GWSM0-SESSION-ERROR", HttpStatus.FORBIDDEN);
		}
		
		// se sessione e in l2 continuo e setto request per creazione applicativa
		AppSessionRequest appRequest = new AppSessionRequest();
		appRequest.setBt(bt);
		appRequest.setVerificato(true);
		// chiamata a fragment per creazione sessione app
		
		ResponseEntity<AppSessionResponse> appResponse = new ResponseEntity<AppSessionResponse>(new AppSessionResponse() ,HttpStatus.OK);
		try {
			appResponse = appSession.getSessionApp(appRequest, sicSesDTO.getSessionId(), request.getAppName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage() + " , " + e.getCause());
			throw new BaseActionException("GWSM0-APP-SESSION-ERROR", appResponse.getStatusCode());
		}
		//funziona
//		AppSessionResponse appResponse = new AppSessionResponse();
//		try {
//			appResponse = appSession.getSessionSec4(appRequest, sicSesDTO.getSessionId(), request.getAppName());
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage() + " , " + e.getCause());
//			throw new BaseActionException("GWSM0-APP-SESSION-ERROR", HttpStatus.DESTINATION_LOCKED);
//		}
		
		response.setAppSecId(appResponse.getBody().getAppSessionId());
//		response.setAppSecId(appResponse.getAppSessionId());
		response.setSecSessId(sicSesDTO.getSessionId());
		if(!Boolean.TRUE.equals(appResponse.getBody().getAlreadyActive()))
			response.setCreated(true);
		else 
			response.setAlreadyActive(true);
		//TODO worka, finire di implementare logica e chiamata ad appsession , ora ce solo controllo su securety session
		return new ResponseEntity<>(response,HttpStatus.OK);
		
		
	}
}

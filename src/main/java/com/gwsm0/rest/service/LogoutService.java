package com.gwsm0.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gwsm0.model.request.LogoutRequest;
import com.gwsm0.model.response.LogoutResponse;
import com.gwsm0.rest.fragment.session.AppSessionFrag;

@Service
public class LogoutService {

	@Autowired
	AppSessionFrag appSession;
	
	public ResponseEntity<LogoutResponse> logout(LogoutRequest request) {
		
		// rimuovo sessione applicativa
		ResponseEntity<LogoutResponse> response = appSession.logout(request.getBt(), null, request.getAppSecId(), request.getSecSessId());
		
		return response;
	}
}

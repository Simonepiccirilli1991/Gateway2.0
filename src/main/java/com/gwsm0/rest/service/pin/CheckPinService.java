package com.gwsm0.rest.service.pin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.wiam.PinWiamRequest;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.CheckPinWiam;

@Service
public class CheckPinService extends BaseActionService<PinRequest, PinResponse>{

	@Autowired
	CheckPinWiam pinWiam;
	@Autowired
	SecuretySessionFrag secSession;;
	
	@Override
	public PinResponse call_(PinRequest iRequest, HttpHeaders httpHeaders) {
		
		PinResponse oResponse = new PinResponse();
		oResponse.setSessionData(iRequest.getSessionData());
		
		PinWiamRequest requestW = new PinWiamRequest();
		requestW.setBt(iRequest.getBt());
		requestW.setPin(iRequest.getPin());
		requestW.setCanale(httpHeaders.getFirst("Channel"));
		requestW.setCf(iRequest.getCf());
		
		pinWiam.checkPin(requestW);
		
		// creo sessione sicurezza l1 - base
		SessionSecRequest secRequest = new SessionSecRequest();
		secRequest.setBt(iRequest.getBt());
		secRequest.setScope("L1");
		secRequest.setUsername(iRequest.getUsername());
		String sessionId = secSession.createSessionSec(secRequest).getSessionId();
		
		oResponse.setSessionId(sessionId);
		oResponse.setAction(ActionConstants.SENDOTPMAIL);
		oResponse.getSessionData().getSession().setSessionId(sessionId);
		oResponse.getSessionData().getSession().setActionId(ActionConstants.SENDOTPMAIL.getId());
		oResponse.setPinOk(true);
		
		return oResponse;
	}

}

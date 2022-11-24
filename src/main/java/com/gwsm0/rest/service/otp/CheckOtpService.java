package com.gwsm0.rest.service.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;
import com.gwsm0.rest.fragment.otp.OtpComponentService;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;

@Service
public class CheckOtpService extends BaseActionService<OtpRequest, OtpResponse>{

	@Autowired
	OtpComponentService otpComponent;
	@Autowired
	SecuretySessionFrag secSession;
	
	@Override
	public OtpResponse call_(OtpRequest iRequest, HttpHeaders httpHeaders) {
		
		OtpResponse response = null;
		try {
			response= otpComponent.checkOtp(iRequest);
		}catch(Exception e){
			throw new BaseActionException("Errore nel check codice otp", HttpStatus.SERVICE_UNAVAILABLE);
		}
		if(ObjectUtils.isEmpty(response))
			throw new BaseActionException("Errore nel check codice otp", HttpStatus.SERVICE_UNAVAILABLE);
		if(!response.getCodiceEsito().equals("00"))
			throw new BaseActionException("Otp non valido", HttpStatus.UNAUTHORIZED);
		
		SessionSecRequest secRequest = new SessionSecRequest();
		secRequest.setBt(iRequest.getBt());
		secRequest.setScope("L2");
		secRequest.setUsername("");
		SessionSecResponse secResp =  secSession.update(secRequest,iRequest.getSessionId());
		if(ObjectUtils.isEmpty(secResp))
			throw new BaseActionException("Error on update current securety session", HttpStatus.FORBIDDEN);
		// se sessione di sicurezza non esiste in l1 utente non puo essere arrivato qui
		if(Boolean.TRUE.equals(secResp.getSessionNoExist()))
			throw new BaseActionException("Error ri effettua login - mancata autorizzazione sicurezza l1", HttpStatus.UNAUTHORIZED);
		
		
		response.setSessionData(iRequest.getSessionData());
		response.getSessionData().getSession().setCanale("WEB");
		response.getSessionData().getSession().setActionId(ActionConstants.CONSENT.getId());;
		response.setAction(ActionConstants.CONSENT);
		
		return response;
		
	}
	
	
}

package com.gwsm0.rest.service.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;
import com.gwsm0.rest.fragment.otp.OtpComponentService;

@Service
public class GenerateOtpService extends BaseActionService<OtpRequest, OtpResponse>{

	@Autowired
	OtpComponentService otpComponent;

	@Override
	public OtpResponse call_(OtpRequest iRequest, HttpHeaders httpHeaders) {
		
		OtpResponse response = new OtpResponse();
		String resp = null;
		try {
			resp= otpComponent.generaOtp(iRequest);
		}catch(Exception e){
			throw new BaseActionException("Errore nella generazione codice otp - riprovare piu tardi", HttpStatus.SERVICE_UNAVAILABLE);
		}
		if(ObjectUtils.isEmpty(resp))
			throw new BaseActionException("Errore nella generazione codice otp - riprovare piu tardi", HttpStatus.SERVICE_UNAVAILABLE);
		
		response.setTrxId(resp);
		response.setMail(true);
		response.setSended(true);
		response.setSessionData(iRequest.getSessionData());
		response.getSessionData().getSession().setCanale("WEB");
		response.getSessionData().getSession().setActionId(ActionConstants.CHECKOTPMAIL.getId());;
		response.setAction(ActionConstants.CHECKOTPMAIL);
		
		return response;
	}
	
	
}

package com.gwsm0.rest.service.pin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.fragment.model.wiam.PinWiamRequest;
import com.gwsm0.fragment.model.wiam.PinWiamResponse;
import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.ChangePinWiam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChangePinService extends BaseActionService<PinRequest, PinResponse>{

	@Autowired
	ChangePinWiam changePinWiam;
	@Autowired
	SecuretySessionFrag secSession;;

	@Override
	public PinResponse call_(PinRequest iRequest, HttpHeaders httpHeaders) {

		PinResponse oResponse = new PinResponse();
		oResponse.setSessionData(iRequest.getSessionData());

		// prima di cambiare pin controllo se sessione e in l2 , in caso contrario mi aspetto un otp da validare 
		SessionSecRequest secReq = new SessionSecRequest();
		secReq.setBt(iRequest.getBt());
		secReq.setUsername(iRequest.getUsername());

		SessionSecResponse secResp = null;
		try {
			secResp = secSession.getSessionSec(secReq);
		}catch(Exception e) {
			log.info("Error on retriving securety session, try to use otp");
		}

		if(ObjectUtils.isEmpty(secResp) && ObjectUtils.isEmpty(iRequest.getOtp())
				|| !secResp.getScope().equals("L2") && ObjectUtils.isEmpty(iRequest.getOtp()))
			throw new BaseActionException("No sec session , and no otp provided", HttpStatus.UNAUTHORIZED);
		else if(!ObjectUtils.isEmpty(secResp) && !secResp.getScope().equals("L2") 
				|| !ObjectUtils.isEmpty(iRequest.getOtp())) {
			//checkOtp fare checkOtp
			//TODO implementare checkOtp
		}


		PinWiamRequest requestW = new PinWiamRequest();
		requestW.setBt(iRequest.getBt());
		requestW.setPin(iRequest.getNewPin());
		requestW.setOldPin(iRequest.getPin());
		requestW.setCanale(httpHeaders.getFirst("Channel"));
		requestW.setCf(iRequest.getCf());

		PinWiamResponse wimaResp = changePinWiam.changePin(requestW);

		oResponse.setPinChanged(wimaResp.getPinChanged());
		oResponse.setAction(ActionConstants.CONSENT);
		oResponse.getSessionData().getSession().setActionId(ActionConstants.CONSENT.getId());

		return oResponse;
	}
}

package com.gwsm0.controller;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.request.EnforcementRequest;
import com.gwsm0.model.response.EnforcementResponse;



@RestController
@RequestMapping("app")
public class AppController {

	
	// setto sicurezza, mappatura 1 a 1
	@RequestMapping("scr/enforcement")
	public EnforcementResponse enforcement(@RequestBody EnforcementRequest request) {
		// controllo parametri in input direttamente qui non avendo command
		if(ObjectUtils.isEmpty(request.getUsername()) ||  ObjectUtils.isEmpty(request.getOtp())  )
			throw new BaseActionException(ActErrors.INVALIDREQUEST);
		
		return null;
	}
}

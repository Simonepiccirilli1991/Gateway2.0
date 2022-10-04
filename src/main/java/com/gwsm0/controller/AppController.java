package com.gwsm0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.constants.AppConstants;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.request.EnforcementRequest;
import com.gwsm0.model.response.ContextResponse;
import com.gwsm0.model.response.EnforcementResponse;
import com.gwsm0.rest.service.ContextService;



@RestController
@RequestMapping("app")
public class AppController {

	@Autowired
	private ContextService contextService;
	// setto sicurezza, mappatura 1 a 1
	@RequestMapping("scr/enforcement")
	public EnforcementResponse enforcement(@RequestBody EnforcementRequest request) {
		// controllo parametri in input direttamente qui non avendo command
		if(ObjectUtils.isEmpty(request.getUsername()) ||  ObjectUtils.isEmpty(request.getOtp())  )
			throw new BaseActionException(ActErrors.INVALIDREQUEST);
		
		return null;
	}
	
	@PostMapping("context/create")
	public ResponseEntity<ContextResponse> contextCreate(@RequestBody ContextRequest request, 
			@RequestHeader HttpHeaders header) {
		
		if(ObjectUtils.isEmpty(request.getUsername()) ||  ObjectUtils.isEmpty(request.getBt()) 
				|| ObjectUtils.isEmpty(header.getFirst(AppConstants.CustomHeader.ABI)) || ObjectUtils.isEmpty(header.getFirst(AppConstants.CustomHeader.APPNAME)) )
			throw new BaseActionException(ActErrors.INVALIDREQUEST);
		
		request.setAbi(header.getFirst(AppConstants.CustomHeader.ABI));
		request.setAppName(header.getFirst(AppConstants.CustomHeader.APPNAME));
		
		return contextService.contextCreate(request);
		
		
		
	}
}

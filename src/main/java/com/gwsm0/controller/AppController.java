package com.gwsm0.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.gwsm0.error.handler.BaseErrorException;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.request.DocumentRequest;
import com.gwsm0.model.request.EnforcementRequest;
import com.gwsm0.model.request.LogoutRequest;
import com.gwsm0.model.response.ContextResponse;
import com.gwsm0.model.response.DocumentResponse;
import com.gwsm0.model.response.EnforcementResponse;
import com.gwsm0.model.response.LogoutResponse;
import com.gwsm0.rest.service.ContextService;
import com.gwsm0.rest.service.DocumentiService;
import com.gwsm0.rest.service.ItextService;
import com.gwsm0.rest.service.LogoutService;



@RestController
@RequestMapping("app")
public class AppController {

	@Autowired
	private ContextService contextService;
	@Autowired LogoutService logoutService;
	@Autowired
	DocumentiService docServ;
	@Autowired
	ItextService itextService;
	
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
	
	@PostMapping("context/logout")
	public ResponseEntity<LogoutResponse> logout(@RequestBody LogoutRequest request,
			@RequestHeader HttpHeaders header){
		
		request.setAppSecId(header.getFirst("X-APP-TOKEN"));
		request.setSecSessId(header.getFirst("X-SEC-TOKEN"));
		
		// controllo qui al volo poi inserisco meglio
		if(ObjectUtils.isEmpty(request.getAppSecId()) || ObjectUtils.isEmpty(request.getSecSessId())
				|| ObjectUtils.isEmpty(request.getBt())) {
			throw new BaseErrorException(HttpStatus.BAD_REQUEST, "INVALID_REQUEST");
			
		}
		
		return logoutService.logout(request);
		
	}
	
	
	@PostMapping("document")
	public ResponseEntity<List<DocumentResponse>> document(@RequestBody DocumentRequest request) throws Exception{
		
		ResponseEntity<List<DocumentResponse>> response = new ResponseEntity<>(docServ.controllaFirme(request),HttpStatus.OK);
		return response;
	}
	
	@PostMapping("document/blank")
	public ResponseEntity<List<DocumentResponse>> documentBlank(@RequestBody DocumentRequest request) throws Exception{
		
		ResponseEntity<List<DocumentResponse>> response = new ResponseEntity<>(docServ.controllaFirmeVuote(request),HttpStatus.OK);
		return response;
	}
	
	@PostMapping("documenti")
	public ResponseEntity<List<DocumentResponse>> documenti(@RequestBody DocumentRequest request) throws Exception{
		
		ResponseEntity<List<DocumentResponse>> response = new ResponseEntity<>(itextService.controllaFirme(request),HttpStatus.OK);
		return response;
	}
}

package com.gwsm0.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwsm0.command.AnagraficaCommand;
import com.gwsm0.command.OtpCommand;
import com.gwsm0.command.PinCommand;
import com.gwsm0.command.StatusCommand;
import com.gwsm0.command.UtenteCommand;
import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.base.BaseActionResponse;
import com.gwsm0.model.request.AnagraficaRequest;
import com.gwsm0.model.request.LoginRequest;
import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.request.UtenteRequest;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.model.response.LoginResponse;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.model.response.StatusResponse;

@RestController
@RequestMapping("action")
public class ActionController {
	
	@Autowired protected BeanFactory beanFactory;
	
	@RequestMapping("status")
	public StatusResponse status(@RequestBody StatusRequest request) throws Exception {

		return beanFactory.getBean(StatusCommand.class, request, null).doExcute();

	}
	
	@RequestMapping("utente")
	public BaseActionResponse salvaUtente(@RequestBody UtenteRequest request) throws Exception {
		
		return beanFactory.getBean(UtenteCommand.class,request,null).doExcute();
		
		
	}
	
	@RequestMapping("anagrafica")
	public AnagraficaResponse anagrafica(@RequestBody AnagraficaRequest request,
			@RequestHeader HttpHeaders httpHeaders) throws Exception{
		
		if(ObjectUtils.isEmpty(request.getUsername()) && ObjectUtils.isEmpty(request.getAction()))
			throw new BaseActionException(ActErrors.INVALIDREQUEST);
		
		if(ObjectUtils.isEmpty(request.getUsername()))
			request.setUsername(httpHeaders.getFirst("USERNAME"));
		
		return beanFactory.getBean(AnagraficaCommand.class, request,null).doExecute();
	}
	
	@RequestMapping("pin")
	public PinResponse pin(@RequestBody PinRequest request, @RequestHeader HttpHeaders header) throws Exception {
		
		return beanFactory.getBean(PinCommand.class, request, header).doExecute();

	}
	@PostMapping("otp")
	public OtpResponse login(@RequestBody OtpRequest request, @RequestHeader HttpHeaders header) throws Exception {
		
		return beanFactory.getBean(OtpCommand.class, request, header).doExecute();
	}

}

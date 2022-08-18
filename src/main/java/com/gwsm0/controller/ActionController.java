package com.gwsm0.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwsm0.command.StatusCommand;
import com.gwsm0.model.base.BaseActionResponse;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.request.UtenteRequest;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.service.StatusService;
import com.gwsm0.rest.service.RegistraUtenteService;

@RestController
@RequestMapping("action")
public class ActionController {
	
	@Autowired protected BeanFactory beanFactory;
	
	@RequestMapping("status")
	public StatusResponse status(@RequestBody StatusRequest request) throws Exception {

		return beanFactory.getBean(StatusCommand.class, request, null).doExcute();

	}
	
	@RequestMapping("registra/utente")
	public BaseActionResponse salvaUtente(@RequestBody UtenteRequest request) {
		
		// TODO implementare command
		return null;
	}

}

package com.gwsm0.rest.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.gwsm0.model.base.BaseActionService;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.request.PinResponse;

@Service
public class CheckPinService extends BaseActionService<PinRequest, PinResponse>{

	@Override
	public PinResponse call_(PinRequest iRequest, HttpHeaders httpHeaders) {
		
		// TODO implementare logica
		
		
		return null;
	}

}

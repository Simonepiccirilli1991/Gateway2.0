package com.gwsm0.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;

@RestController
@RequestMapping("action")
public class ActionController {
	
	@RequestMapping("status")
	public StatusResponse status(@RequestBody StatusRequest request) {
		
		StatusResponse response = new StatusResponse();
		
		return response;
	}

}

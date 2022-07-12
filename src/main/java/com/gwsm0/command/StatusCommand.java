package com.gwsm0.command;

import org.springframework.http.HttpHeaders;

import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.service.StatusService;

public class StatusCommand extends BaseActionCommand<StatusRequest, StatusResponse>{

	public StatusCommand(StatusRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
	}
		public StatusResponse doExcute() throws Exception{
			
			switch (super.iRequest.getAction()) {
			// chiama alla status di wiam
				case STATUS:
					return super.getResponse(StatusService.class);
			default:
				return super.throwDefault();
			}
		
	}

}
	
	

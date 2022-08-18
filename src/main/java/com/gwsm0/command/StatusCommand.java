package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.service.StatusService;

@Component
@Scope("prototype")
public class StatusCommand extends BaseActionCommand<StatusRequest, StatusResponse>{

	public StatusCommand(StatusRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
	}
		public StatusResponse doExcute() throws Exception{
			// TODO da testare
			switch (super.iRequest.getAction()) {
			// chiama alla status di wiam
				case STATUS:
					return super.getResponse(StatusService.class);
			default:
				return super.throwDefault();
			}
		
	}

}
	
	

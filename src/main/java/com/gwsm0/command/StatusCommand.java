package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
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
			switch (super.iRequest.getAction()) {
			// chiama alla status di wiam
				case STATUS:
					if(ObjectUtils.isEmpty(iRequest.getBt()) && ObjectUtils.isEmpty(iRequest.getUsername()))  throw new BaseActionException(ActErrors.INVALIDREQUEST);
					return super.getResponse(StatusService.class);
			default:
				return super.throwDefault();
			}
		
	}

}
	
	

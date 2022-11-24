package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.service.pin.ChangePinService;
import com.gwsm0.rest.service.pin.CheckPinService;

@Component
@Scope("prototype")
public class PinCommand extends BaseActionCommand<PinRequest, PinResponse>{

	public PinCommand(PinRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);
	}


	public PinResponse doExecute() throws Exception {
		switch (super.iRequest.getAction()) {
		// normale checkpin
		case CHECKPIN:
			if(ObjectUtils.isEmpty(iRequest.getPin()) || ObjectUtils.isEmpty(iRequest.getBt())) 
				throw new BaseActionException(ActErrors.INVALIDREQUEST);

			return super.getResponse(CheckPinService.class);		
			//cambio pin
		case CAMBIOPIN:	
			if(ObjectUtils.isEmpty(iRequest.getPin()) || ObjectUtils.isEmpty(iRequest.getBt())
					|| ObjectUtils.isEmpty(iRequest.getNewPin())) 
				throw new BaseActionException(ActErrors.INVALIDREQUEST);

			return super.getResponse(ChangePinService.class);

		case RECUPERAPIN:

		default:
			return super.throwDefault();
		}
	}
}

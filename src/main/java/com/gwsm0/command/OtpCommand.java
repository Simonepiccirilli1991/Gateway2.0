package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;
import com.gwsm0.rest.service.otp.GenerateOtpService;

@Component
@Scope("prototype")
public class OtpCommand extends BaseActionCommand<OtpRequest, OtpResponse>{

	public OtpCommand(OtpRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);

	}

	public OtpResponse doExecute() throws Exception {
		switch (super.iRequest.getAction()) {
		// normale checkpin
		case SENDOTPMAIL:
			if(ObjectUtils.isEmpty(iRequest.getBt()) || ObjectUtils.isEmpty(iRequest.getEmail())
					|| ObjectUtils.isEmpty(iRequest.getProfile()))
				throw new BaseActionException(ActErrors.INVALIDREQUEST);

			return super.getResponse(GenerateOtpService.class);		
			//cambio pin
		case CHECKOTPMAIL:	
			if(ObjectUtils.isEmpty(iRequest.getBt()) || ObjectUtils.isEmpty(iRequest.getProfile())
					|| ObjectUtils.isEmpty(iRequest.getSessionId()) || ObjectUtils.isEmpty(iRequest.getOts()))
				throw new BaseActionException(ActErrors.INVALIDREQUEST);

			return super.getResponse(GenerateOtpService.class);		

		default:
			return super.throwDefault();
		}
	}
}

package com.gwsm0.command;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.base.BaseActionResponse;
import com.gwsm0.model.request.UtenteRequest;
import com.gwsm0.rest.service.RegistraUtenteService;

@Component
@Scope("prototype")
public class UtenteCommand extends BaseActionCommand<UtenteRequest, BaseActionResponse>{

	public UtenteCommand(UtenteRequest iRequest, HttpHeaders httpHeaders) {
		super(iRequest, httpHeaders);

	}


	public BaseActionResponse doExcute() throws Exception{
		switch (super.iRequest.getAction()) {
		// chiama alla status di wiam
		case REGISTRATI:
			if(ObjectUtils.isEmpty(iRequest.getBt()) || ObjectUtils.isEmpty(iRequest.getAbi()) 
					|| ObjectUtils.isEmpty(iRequest.getCellulare()) || ObjectUtils.isEmpty(iRequest.getEmail())
					|| ObjectUtils.isEmpty(iRequest.getPin()) || ObjectUtils.isEmpty(iRequest.getUsername()))
				throw new BaseActionException(ActErrors.INVALIDREQUEST);

			return super.getResponse(RegistraUtenteService.class);
			//TODO implementare modifica utente
			
		//check utente esiste - usata per vedere se username gia in uso
		case CHECKUTENTE:	

		default:
			return super.throwDefault();
		}

	}

}

package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gwsm0.fragment.model.session.AppSessionRequest;
import com.gwsm0.fragment.model.session.AppSessionResponse;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.response.ContextResponse;
import com.gwsm0.rest.fragment.session.AppSessionFrag;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.service.ContextService;

@SpringBootTest
public class AppControllerTest {

	
	@MockBean
	AppSessionFrag appSess;
	
	@MockBean 
	SecuretySessionFrag secSess;
	
	@Autowired
	private ContextService contexServ;
	
	@Test
	public void contextTestOk() {
		
		ContextRequest request = new ContextRequest();
		request.setAbi("abi");
		request.setAppName("appName");
		request.setUsername("username");
		
		// sessione sicurezza
		SessionSecResponse responseSicurezza = new SessionSecResponse();
		responseSicurezza.setAbi("abi");
		responseSicurezza.setBt("bt");
		responseSicurezza.setScope("L2");
		responseSicurezza.setSessionId("sessionId");
		responseSicurezza.setSessionActive(true);
		
		// response di sessione applicativa
		ResponseEntity<AppSessionResponse> appResponse = new ResponseEntity<AppSessionResponse>(new AppSessionResponse() ,HttpStatus.OK);
		appResponse.getBody().setActive(true);
		appResponse.getBody().setCodServizio("00");
		appResponse.getBody().setAppSessionId("sessionId");
		
		Mockito.when(secSess.getSessionSec(ArgumentMatchers.any(SessionSecRequest.class))).thenReturn(responseSicurezza);
		
		Mockito.when(appSess.getSessionAppAdnError((ArgumentMatchers.any(AppSessionRequest.class)), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(appResponse);
		
		
		ResponseEntity<ContextResponse> contextCreate = contexServ.contextCreate(request);
		
		assertThat(contextCreate.getBody().getAppSecId()).isSameAs("sessionId");
		assertThat(contextCreate.getStatusCode()).isSameAs(HttpStatus.OK);
	}
}


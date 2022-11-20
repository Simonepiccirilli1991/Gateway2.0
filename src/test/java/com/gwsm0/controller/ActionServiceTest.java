package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.gwsm0.constants.ActionConstants;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.fragment.model.wiam.PinWiamRequest;
import com.gwsm0.fragment.model.wiam.PinWiamResponse;
import com.gwsm0.model.base.Session;
import com.gwsm0.model.base.SessionData;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.CheckPinWiam;
import com.gwsm0.rest.service.CheckPinService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionServiceTest {

	@Autowired
	CheckPinService pinService;
	@MockBean
	CheckPinWiam pinWiam;
	@MockBean
	SecuretySessionFrag secSession;

	
	@Test
	public void checkPinServiceOK() {
		
		PinRequest request = new PinRequest();
		request.setSessionData(new SessionData());
		request.getSessionData().setSession(new Session());
		request.setBt("bt-123");
		request.setCf("cf-123");
		request.setUsername("username-123");
		
		when(pinWiam.checkPin(any(PinWiamRequest.class))).thenReturn(new PinWiamResponse());
		
		when(secSession.createSessionSec(any(SessionSecRequest.class))).thenReturn(new SessionSecResponse());
		
		HttpHeaders header = new HttpHeaders();
		PinResponse response = pinService.call_(request, header);
		
		assertThat(response.getAction().equals(ActionConstants.CONSENT));
		
		
	}
}

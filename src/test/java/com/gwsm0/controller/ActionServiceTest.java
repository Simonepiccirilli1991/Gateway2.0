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
import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.rest.fragment.otp.OtpComponentService;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.ChangePinWiam;
import com.gwsm0.rest.fragment.wiam.CheckPinWiam;
import com.gwsm0.rest.service.otp.CheckOtpService;
import com.gwsm0.rest.service.otp.GenerateOtpService;
import com.gwsm0.rest.service.pin.ChangePinService;
import com.gwsm0.rest.service.pin.CheckPinService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionServiceTest {
	
	@Autowired
	CheckOtpService otpCheckService;
	@Autowired
	GenerateOtpService generateOtp;
	@Autowired
	CheckPinService pinService;
	@Autowired
	ChangePinService changePinService;
	@MockBean
	CheckPinWiam pinWiam;
	@MockBean
	SecuretySessionFrag secSession;
	@MockBean
	ChangePinWiam changePinWiam;
	@MockBean
	OtpComponentService otpComponent;

	//checkpin
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
		
		assertThat(response.getAction()).isEqualTo(ActionConstants.SENDOTPMAIL);
		
		
	}
	
	// ChangePin test
	
	public void changePinOK() {

		PinRequest request = new PinRequest();
		request.setPin("1234");
		request.setNewPin("123456");
		request.setBt("bt");
		request.setAction(ActionConstants.CAMBIOPIN);
		request.setSessionData(new SessionData());
		request.getSessionData().setSession(new Session());
		request.setSessionId("sessionId");
		
		SessionSecResponse sessResp = new SessionSecResponse();
		sessResp.setScope("L2");
		
		PinWiamResponse pinResp = new PinWiamResponse();
		pinResp.setPinChanged(true);
		when(secSession.getSessionSec(any(SessionSecRequest.class))).thenReturn(sessResp);
		
		when(changePinWiam.changePin(any(PinWiamRequest.class))).thenReturn(pinResp);
		HttpHeaders header = new HttpHeaders();
		
		PinResponse response = changePinService.call_(request, header);

		assertThat(response.getAction()).isEqualTo(ActionConstants.CONSENT);


	}
	
	
	// sendOtp test
	@Test
	public void sendOtpOK() {
		
		OtpRequest request = new OtpRequest();
		request.setAction(ActionConstants.SENDOTPMAIL);
		request.setBt("bt-prova");
		request.setEmail("email");
		request.setProfile("WEB");
		request.setSessionId("12345");
		request.setSessionData(new SessionData());
		request.getSessionData().setSession(new Session());
		
		String  trxId = "1234";
		when(otpComponent.generaOtp(any(OtpRequest.class))).thenReturn(trxId);
		
		OtpResponse response = generateOtp.call_(request, null);
		
		assertThat(response.getAction()).isEqualTo(ActionConstants.CHECKOTPMAIL);
		assertThat(response.getTrxId()).isEqualTo("1234");
	}
	
	
	// CheckOtp test
	
	@Test
	public void checkOtpOK() {
		
		OtpRequest request = new OtpRequest();
		request.setAction(ActionConstants.CHECKOTPMAIL);
		request.setBt("bt-prova");
		request.setEmail("email");
		request.setProfile("WEB");
		request.setSessionId("12345");
		request.setOts("1111");
		request.setSessionData(new SessionData());
		request.getSessionData().setSession(new Session());
		
		OtpResponse resp = new OtpResponse();
		resp.setCodiceEsito("00");
		
		SessionSecResponse secResp = new SessionSecResponse();
		secResp.setSessionId("12345");
		when(otpComponent.checkOtp(any(OtpRequest.class))).thenReturn(resp);
		
		when(secSession.update(any(SessionSecRequest.class), anyString())).thenReturn(secResp);
		
		OtpResponse response = otpCheckService.call_(request, null);
		
		assertThat(response.getAction()).isEqualTo(ActionConstants.CONSENT);
	}
}

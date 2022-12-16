package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwsm0.BaseTest;
import com.gwsm0.constants.ActionConstants;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.fragment.model.wiam.AnagraficaWResponse;
import com.gwsm0.fragment.model.wiam.EnforcementWRequest;
import com.gwsm0.fragment.model.wiam.EnforcementWResponse;
import com.gwsm0.fragment.model.wiam.PinWiamRequest;
import com.gwsm0.fragment.model.wiam.PinWiamResponse;
import com.gwsm0.fragment.model.wiam.StatusWResponse;
import com.gwsm0.model.base.Session;
import com.gwsm0.model.base.SessionData;
import com.gwsm0.model.request.AnagraficaRequest;
import com.gwsm0.model.request.EnforcementRequest;
import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;
import com.gwsm0.model.request.PinRequest;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.model.response.EnforcementResponse;
import com.gwsm0.model.response.PinResponse;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.fragment.otp.OtpComponentService;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.AnagraficaWiam;
import com.gwsm0.rest.fragment.wiam.ChangePinWiam;
import com.gwsm0.rest.fragment.wiam.CheckPinWiam;
import com.gwsm0.rest.fragment.wiam.EnforcementWiam;
import com.gwsm0.rest.fragment.wiam.StatusWiam;
import com.gwsm0.rest.service.EnforcementService;
import com.gwsm0.rest.service.StatusService;
import com.gwsm0.rest.service.anagrafica.AnagraficaAddService;
import com.gwsm0.rest.service.otp.CheckOtpService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest extends BaseTest{
	
	@Autowired
	MockMvc mvc;
	// anagrafica
	@Test
	public void anagraficaGetOK() throws JsonProcessingException, Exception {
		// setto request
		AnagraficaRequest request = new AnagraficaRequest();
		request.setAction(ActionConstants.ANAGRAFICAGET);
		request.setUsername("PROVA");
		request.setSessionData(new SessionData());
		//setto anagrafica
		AnagraficaWRequest anagrafica = new AnagraficaWRequest();

		anagrafica.setCodiceFiscale("PCCRV");
		anagrafica.setCognome("Cognome");
		anagrafica.setComune("Comune");
		anagrafica.setDataNascita("DATA/nascita");
		anagrafica.setNazionalità("Nazionalità");
		anagrafica.setNome("nome");

		// setto response
		AnagraficaResponse response = new AnagraficaResponse();
		response.setAnagrafica(new AnagraficaWRequest());
		response.getAnagrafica().setCodiceFiscale("PCCRV");
		response.getAnagrafica().setCognome("Cognome");
		response.getAnagrafica().setComune("Comune");
		response.getAnagrafica().setDataNascita("DATA/nascita");
		response.getAnagrafica().setNazionalità("Nazionalità");
		response.getAnagrafica().setNome("nome");

		Mockito.when(anagWiam.getAnagrafica(anyString())).thenReturn(response);

		request.setAnagrafica(anagrafica);
		String mock =  mvc.perform(post("/action/anagrafica")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		AnagraficaResponse fResp = mapper.readValue(mock, AnagraficaResponse.class);
		System.out.println(fResp);
	}

	@Test
	public void anagraficaAddOK() throws JsonProcessingException, Exception {
		// setto request
		AnagraficaRequest request = new AnagraficaRequest();
		request.setAction(ActionConstants.ANAGRAFICAADD);
		request.setUsername("PROVA");
		request.setSessionData(new SessionData());
		//setto anagrafica
		AnagraficaWRequest anagrafica = new AnagraficaWRequest();

		anagrafica.setCodiceFiscale("PCCRV");
		anagrafica.setCognome("Cognome");
		anagrafica.setComune("Comune");
		anagrafica.setDataNascita("DATA/nascita");
		anagrafica.setNazionalità("Nazionalità");
		anagrafica.setNome("nome");

		// setto response
		AnagraficaWResponse response = new AnagraficaWResponse();
		response.setAnagrafica(anagrafica);

		Mockito.when(anagWiam.addAnagrafica(any(AnagraficaWRequest.class))).thenReturn(response);
		//prova
		//Mockito.when(rt.postForObject(Mockito.eq("http://localhost:8083/wiam/add/anagrafica"), any(Object.class),Mockito.eq(AnagraficaWResponse.class))).thenReturn(response);

		request.setAnagrafica(anagrafica);
		//		
		String oResp = mvc.perform(post("/action/anagrafica")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		AnagraficaResponse fResp = mapper.readValue(oResp, AnagraficaResponse.class);
		System.out.println(fResp);
	}

	@Test
	public void anagraficaServiceOK() {
		// setto request
		AnagraficaRequest request = new AnagraficaRequest();
		request.setAction(ActionConstants.ANAGRAFICAADD);
		request.setUsername("PROVA");
		request.setSessionData(new SessionData());
		//setto anagrafica
		AnagraficaWRequest anagrafica = new AnagraficaWRequest();

		anagrafica.setCodiceFiscale("PCCRV");
		anagrafica.setCognome("Cognome");
		anagrafica.setComune("Comune");
		anagrafica.setDataNascita("DATA/nascita");
		anagrafica.setNazionalità("Nazionalità");
		anagrafica.setNome("nome");

		// setto response
		AnagraficaWResponse response = new AnagraficaWResponse();
		response.setAnagrafica(anagrafica);

		Mockito.when(anagWiam.addAnagrafica(any(AnagraficaWRequest.class))).thenReturn(response);
		System.out.println(response.getAnagrafica().toString());
		System.out.println(Mockito.when(anagWiam.addAnagrafica(any(AnagraficaWRequest.class))).thenReturn(response).toString());
		System.out.println(anagWiam.addAnagrafica(anagrafica).getAnagrafica().toString());
		//AnagraficaResponse oResponse = anagAdd.call(request, null);
		AnagraficaResponse oResponse = new AnagraficaResponse();
		// se non mocko diretto va in nullpoint al momento
		oResponse.setAnagrafica(anagWiam.addAnagrafica(anagrafica).getAnagrafica());
		assertThat(oResponse.getAnagrafica().getCodiceFiscale()).isSameAs("PCCRV");
	}

	// checkPin COntrolelr test

	@Test
	public void checkPinOk() throws Exception {

		PinRequest request = new PinRequest();
		request.setSessionData(new SessionData());
		request.getSessionData().setSession(new Session());
		request.setBt("bt-123");
		request.setCf("cf-123");
		request.setPin("pin");
		request.setUsername("username-123");
		request.setAction(ActionConstants.CHECKPIN);
		request.getSessionData().getSession().setActionId(ActionConstants.CHECKPIN.getId());;


		when(pinWiam.checkPin(any(PinWiamRequest.class))).thenReturn(new PinWiamResponse());

		when(secSession.createSessionSec(any(SessionSecRequest.class))).thenReturn(new SessionSecResponse());

		String mock =  mvc.perform(post("/action/pin")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		PinResponse response = mapper.readValue(mock, PinResponse.class);
		assertThat(response.getAction()).isEqualTo(ActionConstants.SENDOTPMAIL);

	}

	// ChangePin test
	@Test
	public void changePinOK() throws Exception {
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

		String mock =  mvc.perform(post("/action/pin")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		PinResponse response = mapper.readValue(mock, PinResponse.class);
		assertThat(response.getAction()).isEqualTo(ActionConstants.CONSENT);
	}

	// SendOtp test
	@Test
	public void sendOtpOK() throws Exception {

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

		String mock =  mvc.perform(post("/action/otp")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		OtpResponse response = mapper.readValue(mock, OtpResponse.class);
		assertThat(response.getAction()).isEqualTo(ActionConstants.CHECKOTPMAIL);
		assertThat(response.getTrxId()).isEqualTo("1234");
	}

	@Test
	public void checkOtpOK() throws Exception {

		OtpRequest request = new OtpRequest();
		request.setAction(ActionConstants.CHECKOTPMAIL);
		request.setBt("bt-prova");
		request.setEmail("email");
		request.setProfile("WEB");
		request.setSessionId("12345");
		request.setOts("1111");
		request.setTrxId("12345");
		request.setSessionData(new SessionData());
		request.getSessionData().setSession(new Session());

		OtpResponse resp = new OtpResponse();
		resp.setCodiceEsito("00");

		SessionSecResponse secResp = new SessionSecResponse();
		secResp.setSessionId("12345");

		when(otpComponent.checkOtp(any(OtpRequest.class))).thenReturn(resp);

		when(secSession.update(any(SessionSecRequest.class), anyString())).thenReturn(secResp);

		String mock =  mvc.perform(post("/action/otp")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		//		OtpResponse response = mapper.readValue(mock, OtpResponse.class);
		//		assertThat(response.getAction()).isEqualTo(ActionConstants.CONSENT);

	}

}

package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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
import com.gwsm0.rest.fragment.wiam.ChangePinWiam;
import com.gwsm0.rest.fragment.wiam.CheckPinWiam;
import com.gwsm0.rest.service.otp.CheckOtpService;
import com.gwsm0.rest.service.otp.GenerateOtpService;
import com.gwsm0.rest.service.pin.ChangePinService;
import com.gwsm0.rest.service.pin.CheckPinService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionServiceTest extends BaseTest{

	// status test
	@Test
	public void statusOK() throws Exception {

		StatusWResponse r = new StatusWResponse();
		r.setUtenteRegistrato(true);
		r.setAnagragicaP(false);

		StatusResponse response = new StatusResponse();
		response.setUtenteRegistrato(true);
		StatusRequest request = new StatusRequest();
		request.setSessionData(new SessionData());

		when(statusWiam.checkStatus(any(StatusRequest.class))).thenReturn(r);
		response = statusService.call_(request, null);

		assertThat(response.getAction()).isSameAs(ActionConstants.ANAGRAFICAADD);

		// prova a cascata

		r.setAnagragicaP(true);
		r.setSicuriro(false);
		when(statusWiam.checkStatus(any(StatusRequest.class))).thenReturn(r);
		response = statusService.call_(request, null);

		assertThat(response.getAction()).isSameAs(ActionConstants.SICUREZZA);

		// enforce
		r.setSicuriro(true);
		r.setEnforced(false);
		when(statusWiam.checkStatus(any(StatusRequest.class))).thenReturn(r);
		response = statusService.call_(request, null);

		assertThat(response.getAction()).isSameAs(ActionConstants.ENFORCEMENT);

		// consent
		// enforce
		r.setSicuriro(true);
		r.setEnforced(true);
		when(statusWiam.checkStatus(any(StatusRequest.class))).thenReturn(r);
		response = statusService.call_(request, null);

		assertThat(response.getAction()).isSameAs(ActionConstants.CONSENT);
	}

	// enforcement test
	@Test
	public void enforcementOk() {

		EnforcementWResponse oResponse = new EnforcementWResponse();
		oResponse.setCodiceEsito("ok");
		oResponse.setSeed("seed");
		oResponse.setTokenRicordami("token");

		EnforcementRequest request = new EnforcementRequest();
		EnforcementResponse response = new EnforcementResponse();

		Mockito.when(enforcement.enforcement(any(EnforcementWRequest.class))).thenReturn(oResponse);

		response = enforcementSer.enforcement(request);

		assertThat(response.getTokenRicordami()).isSameAs("token");
		assertThat(response.getSeedEnforcement()).isSameAs("seed");
		assertThat(response.getMsgResponse()).isSameAs("ENFORCEMENT AVVENUTO CON SUCCESSO");

	}
	// anagrafica test
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
		//AnagraficaResponse oResponse = anagAdd.call(request, null);
		AnagraficaResponse oResponse = new AnagraficaResponse();
		// se non mocko diretto va in nullpoint al momento
		oResponse.setAnagrafica(anagWiam.addAnagrafica(anagrafica).getAnagrafica());
		assertThat(oResponse.getAnagrafica().getCodiceFiscale()).isSameAs("PCCRV");
	}

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

		//assertThat(response.getAction()).isEqualTo(ActionConstants.CONSENT);
	}
	
	// anagrafica
	@Test
	public void retriveAnagraficaTestOK() {
		
		AnagraficaWResponse iResp = new AnagraficaWResponse();
		
		AnagraficaWRequest anagrafica1 = new AnagraficaWRequest();
		anagrafica1.setCodiceFiscale("cf");
		anagrafica1.setCognome("prova");
		anagrafica1.setComune("roma");
		anagrafica1.setDataNascita("11/11/11");
		anagrafica1.setNazionalità("bob");
		anagrafica1.setNome("nome");
		
		AnagraficaWRequest anagrafica2 = new AnagraficaWRequest();
		anagrafica2.setCodiceFiscale("cf2");
		anagrafica2.setCognome("prova2");
		anagrafica2.setComune("roma2");
		anagrafica2.setDataNascita("11/11/112");
		anagrafica2.setNazionalità("bob2");
		anagrafica2.setNome("nome2");
		
		iResp.setListaAnagrafica(new ArrayList<>());
		iResp.getListaAnagrafica().add(anagrafica2); 
		iResp.getListaAnagrafica().add(anagrafica1); 
		
		when(anagWiam.getAllAnagbyNameoCf(anyString(), anyString())).thenReturn(iResp);
		
		AnagraficaResponse resp = retriveAnagService.retriveCodiceConto("", "", "prova");
		
		assertThat(resp.getAnagrafica().getCodiceFiscale()).isEqualTo("cf");
	}

}

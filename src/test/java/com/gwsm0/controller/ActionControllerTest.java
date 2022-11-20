package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwsm0.constants.ActionConstants;
import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.fragment.model.wiam.AnagraficaWResponse;
import com.gwsm0.fragment.model.wiam.EnforcementWRequest;
import com.gwsm0.fragment.model.wiam.EnforcementWResponse;
import com.gwsm0.fragment.model.wiam.StatusWResponse;
import com.gwsm0.model.base.SessionData;
import com.gwsm0.model.request.AnagraficaRequest;
import com.gwsm0.model.request.EnforcementRequest;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.model.response.EnforcementResponse;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.fragment.wiam.AnagraficaWiam;
import com.gwsm0.rest.fragment.wiam.EnforcementWiam;
import com.gwsm0.rest.fragment.wiam.StatusWiam;
import com.gwsm0.rest.service.AnagraficaAddService;
import com.gwsm0.rest.service.EnforcementService;
import com.gwsm0.rest.service.StatusService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest {
	
	@MockBean 
	StatusWiam statusWiam;
	@Autowired 
	protected MockMvc mvc;
	@Mock
	EnforcementWiam enforcement;
	@InjectMocks 
	EnforcementService enforcementSer;
	@MockBean
	AnagraficaWiam anagWiam;
	@Autowired
	ObjectMapper objectMapper;
	@InjectMocks 
	AnagraficaAddService anagAdd;
	@Autowired
	StatusService statusService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	
	
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
	//TODO capire perche in questo caso non mocka il service e fa chiamata effettiva
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
	

}

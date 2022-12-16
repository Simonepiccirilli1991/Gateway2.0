package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwsm0.BaseTest;
import com.gwsm0.fragment.model.session.AppSessionRequest;
import com.gwsm0.fragment.model.session.AppSessionResponse;
import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.fragment.model.wiam.AnagraficaWResponse;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.model.response.ContextResponse;
import com.gwsm0.rest.fragment.session.AppSessionFrag;
import com.gwsm0.rest.fragment.session.SecuretySessionFrag;
import com.gwsm0.rest.fragment.wiam.AnagraficaWiam;
import com.gwsm0.rest.service.ContextService;


@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	AppSessionFrag appSess;
	@MockBean 
	SecuretySessionFrag secSess;
	@Autowired
	ContextService contexServ;
	@MockBean
	AnagraficaWiam anagWiam;
	
	ObjectMapper mapper = new ObjectMapper();
	
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
	
	@Test
	public void contextControllerTestOk() throws Exception {
		
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
		
		String mock =  mvc.perform(post("http://localhost:8084/app/context/create")
				.contentType("application/json")
				.header("abi", "abi")
				.header("appname", "appname")
//				.content(objectMapper.writeValueAsString(iRequest))
				.content("{\r\n" + 
						"    \"username\":\"ciao\",\r\n" + 
						"    \"bt\":\"bt\"\r\n" + 
						"}")
				.characterEncoding("UTF-8"))
				.andExpect(status().isOk())
				.andReturn().getResponse()
				.getContentAsString();
		
		System.out.println(mock);
	}
	
	// retriveAnagragica 
	
	@Test
	public void retriveAnagraficaTestOk() throws Exception {
		
		AnagraficaWResponse iResp = new AnagraficaWResponse();
		
		AnagraficaWRequest anagrafica1 = new AnagraficaWRequest();
		anagrafica1.setCodiceFiscale("cf");
		anagrafica1.setCognome("prova");
		anagrafica1.setComune("roma");
		anagrafica1.setDataNascita("11/11/11");
		anagrafica1.setNazionalità("bob");
		anagrafica1.setNome("nome");
		
		AnagraficaWRequest anagrafica2 = new AnagraficaWRequest();
		anagrafica2.setCodiceFiscale("kfnn2");
		anagrafica2.setCognome("andonio");
		anagrafica2.setComune("roma2");
		anagrafica2.setDataNascita("11/11/112");
		anagrafica2.setNazionalità("bob2");
		anagrafica2.setNome("turturri");
		
		iResp.setListaAnagrafica(new ArrayList<>());
		iResp.getListaAnagrafica().add(anagrafica2); 
		iResp.getListaAnagrafica().add(anagrafica1); 
		
		when(anagWiam.getAllAnagbyNameoCf(anyString(), anyString())).thenReturn(iResp);
		
		String mock =  mvc.perform(get("http://localhost:8084/app/get/nome/info/andonio/anag")
				.contentType("application/json")
				.header("abi", "abi")
				.header("checks", "andonio")
				.characterEncoding("UTF-8"))
				.andExpect(status().isOk())
				.andReturn().getResponse()
				.getContentAsString();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AnagraficaResponse resp = mapper.readValue(mock, AnagraficaResponse.class);
		assertThat(resp.getAnagrafica().getCodiceFiscale()).isEqualTo("kfnn2");
	}
	
}


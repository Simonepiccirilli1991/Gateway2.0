package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.gwsm0.fragment.model.wiam.StatusWResponse;
import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.fragment.wiam.StatusWiam;
import com.gwsm0.rest.service.StatusService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest {
	
	@Mock StatusWiam statusWiam;
	@Autowired protected MockMvc mvc;
	@InjectMocks StatusService statusService;
	@Mock RestTemplate rt;
	
	
	@Test
	public void statusOK() throws Exception {
		
		StatusWResponse r = new StatusWResponse();
		r.setUtenteRegistrato(true);
		r.setAnagragicaP(true);
		
		StatusResponse response = new StatusResponse();
		response.setUtenteRegistrato(true);
		StatusRequest request = new StatusRequest();
		// Mocko status principale per vedere se gira
		//when(statusService.call_(ArgumentMatchers.any(StatusRequest.class), any(HttpHeaders.class))).thenReturn(response);
		
		Mockito.when(rt.postForObject(Mockito.eq("http://localhost:8083/wiam/status"), any(Object.class),Mockito.eq(StatusWResponse.class))).thenReturn(r);
//		Mockito.when(statusWiam.statusW(any(StatusRequest.class))).thenReturn(response);
		//TODO capire per quale cazzo di motivo ogni volta che chiamo il servici nn torna oggetto dovuto
		response = statusService.call_(request, null);
		
		assertThat(response.getAction()).isSameAs("ANAGRAFICA");
	}

}

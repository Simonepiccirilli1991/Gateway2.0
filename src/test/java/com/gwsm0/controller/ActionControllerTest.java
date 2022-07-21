package com.gwsm0.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import com.gwsm0.model.request.StatusRequest;
import com.gwsm0.model.response.StatusResponse;
import com.gwsm0.rest.fragment.wiam.StatusWiam;
import com.gwsm0.rest.service.StatusService;

@SpringBootTest
@AutoConfigureMockMvc
public class ActionControllerTest {
	
	@Mock StatusWiam statusWiam;
	
	@Mock StatusService statusService;
	
	
	
	@Test
	public void statusOK() {
		
		StatusResponse response = new StatusResponse();
		
		response.setUtenteRegistrato(true);
		response.setAnagragicaP(false);
		
		
		StatusRequest request = new StatusRequest();
		// Mocko status principale per vedere se gira
		when(statusService.call_(ArgumentMatchers.any(StatusRequest.class), any(HttpHeaders.class))).thenReturn(response);
		
		when(statusWiam.statusW(ArgumentMatchers.any(StatusRequest.class))).thenReturn(response);
		//TODO capire per quale cazzo di motivo ogni volta che chiamo il servici nn torna oggetto dovuto
		response = statusService.call_(request, null);
		
		assertThat(response.getAction()).isSameAs("ANAGRAFICA");
	}

}

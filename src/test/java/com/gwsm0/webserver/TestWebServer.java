package com.gwsm0.webserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.json.JSONString;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwsm0.CoreTestSpringConfiguration;
import com.gwsm0.fragment.model.session.SessionSecResponse;
import com.gwsm0.model.request.ContextRequest;
import com.gwsm0.model.response.ContextResponse;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


//@WebAppConfiguration
//@SpringBootTest
@ContextConfiguration(classes = {CoreTestSpringConfiguration.class})
@WebMvcTest
public class TestWebServer {

	static MockWebServer mockWebServer;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@BeforeAll
	static void beforeAll() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start(8083);;;
	}

	@AfterAll
	static void afterAll() throws IOException {
		mockWebServer.shutdown();
	}
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void firstTest() throws JsonProcessingException, Exception {
		
		SessionSecResponse response1 = new SessionSecResponse();
		response1.setScope("L2");
		response1.setBt("bt");
		response1.setSessionId("sessionId");
		response1.setUsername("username");
		
		ContextRequest iRequest = new ContextRequest();
		
		iRequest.setBt("bt");
		iRequest.setUsername("username");
		mockWebServer.enqueue(new MockResponse()
				.setResponseCode(200)
				.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.setBody(objectMapper.writeValueAsString(response1)));
		
		
		String finalResp = mockMvc.perform(post("/app/context/create")
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

				
		
		System.out.println(finalResp);
	}
	
	
	
	
	
}

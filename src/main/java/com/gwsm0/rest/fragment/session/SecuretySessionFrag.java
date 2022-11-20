package com.gwsm0.rest.fragment.session;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gwsm0.fragment.model.session.SessionSecRequest;
import com.gwsm0.fragment.model.session.SessionSecResponse;

import reactor.core.publisher.Mono;

@Service
public class SecuretySessionFrag {

	WebClient webClient = WebClient.create("http://localhost:8083");
	
	// get
	public SessionSecResponse getSessionSec(SessionSecRequest request) {
	
	Mono<SessionSecResponse> sessionDTO = webClient.post()
			.uri("/session/get")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.body(Mono.just(request), SessionSecRequest.class)
			.retrieve()
			.bodyToMono(SessionSecResponse.class);
	
	
	SessionSecResponse response = sessionDTO.share().block();
	
	return response;
	}
	// create
	public SessionSecResponse createSessionSec(SessionSecRequest request) {
		
		Mono<SessionSecResponse> sessionDTO = webClient.post()
				.uri("/session/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(request), SessionSecRequest.class)
				.retrieve()
				.bodyToMono(SessionSecResponse.class);
		
		
		SessionSecResponse response = sessionDTO.share().block();
		
		return response;
		}
	
	
}

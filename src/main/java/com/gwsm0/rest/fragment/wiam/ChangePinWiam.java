package com.gwsm0.rest.fragment.wiam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.fragment.model.wiam.PinWiamRequest;
import com.gwsm0.fragment.model.wiam.PinWiamResponse;

import reactor.core.publisher.Mono;

@Service
public class ChangePinWiam {

	@Value("${configuration.wiam.end-point}")
	private String wiamUri;
	WebClient webClient = WebClient.create(wiamUri);

	
	// CHECKPIN WIAM
	public PinWiamResponse changePin(PinWiamRequest request) {

		Mono<PinWiamResponse>  response = null;
		PinWiamResponse resp = new PinWiamResponse();
		
		try {
			response = webClient.post()
					.uri("/changepin")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.body(Mono.justOrEmpty(request), PinWiamRequest.class)
					.retrieve().bodyToMono(PinWiamResponse.class);
			
		}catch(Exception e) {
			throw new BaseActionException("GWSM0-GENERIC-ERROR", HttpStatus.SERVICE_UNAVAILABLE);
		}
		resp = response.block();
		if(!ObjectUtils.isEmpty(resp.getIsError()))
			throw new BaseActionException(resp.getCodiceEsito(), HttpStatus.valueOf(500));
		
		return resp;
	}
}

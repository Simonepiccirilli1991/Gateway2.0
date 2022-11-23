package com.gwsm0.rest.fragment.otp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gwsm0.model.request.OtpRequest;
import com.gwsm0.model.request.OtpResponse;

import reactor.core.publisher.Mono;

@Service
public class OtpComponentService {

	
	WebClient webClient = WebClient.create("http://localhost:8083");
	
	public String generaOtp(OtpRequest request) {
		Mono<String> sessionDTO = webClient.post()
				.uri("/cache/otp/insert/2")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.justOrEmpty(request), OtpRequest.class)
				.retrieve().bodyToMono(String.class);

		return sessionDTO.share().block();

	}
	
	
	public OtpResponse checkOtp(OtpRequest request) {
		Mono<OtpResponse> sessionDTO = webClient.post()
				.uri("/cache/otp/check")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.justOrEmpty(request), OtpRequest.class)
				.retrieve().bodyToMono(OtpResponse.class);

		return sessionDTO.share().block();

	}
}

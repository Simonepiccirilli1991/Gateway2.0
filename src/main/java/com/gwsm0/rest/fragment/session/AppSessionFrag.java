package com.gwsm0.rest.fragment.session;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.gwsm0.fragment.model.session.AppSessionRequest;
import com.gwsm0.fragment.model.session.AppSessionResponse;
import com.gwsm0.rest.error.BaseActionException;

import reactor.core.publisher.Mono;

@Service
public class AppSessionFrag {

	WebClient webClient = WebClient.create("http://localhost:8083");
	
	// worka ma non torna header
	public AppSessionResponse getSessionSec4(AppSessionRequest request,String secSessionId, String appName) {


		// a differenza di altra chiamata ho bisogno di fare una retrive degli header,
		// quindi anziche usare il metodo retrive 
		Mono<AppSessionResponse> sessionDTO = webClient.post()
				.uri("/app/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// setto header da passare al momento
				.header("SEC_SESSION", secSessionId)
				.header("APP_NAME", appName)
				.body(Mono.justOrEmpty(request), AppSessionRequest.class)
				.retrieve().bodyToMono(AppSessionResponse.class);
		//				.toEntity(AppSessionResponse.class)
		//				.filter(
		//						 entity ->
		//			                entity.getStatusCode().is2xxSuccessful()
		//			                    && entity.getBody() != null
		//			                    && entity.getBody().getActive())
		//			        .flatMap(entity -> Mono.justOrEmpty(entity.getHeaders().getFirst("appTime")));
		
		return sessionDTO.share().block();

	}
	
	// worka ma fa piu chiamate , non capisco perchè
	public ResponseEntity<AppSessionResponse> getSessionApp(AppSessionRequest request,String secSessionId, String appName) {

		Mono<ResponseEntity<AppSessionResponse>> sessionDTO = webClient.post()
				.uri("/app/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// setto header da passare al momento
				.header("SEC_SESSION", secSessionId)
				.header("APP_NAME", appName)
				.body(Mono.justOrEmpty(request), AppSessionRequest.class)
				.retrieve().toEntity(AppSessionResponse.class);

		HttpHeaders header = sessionDTO.block().getHeaders();
		HttpStatus status = sessionDTO.block().getStatusCode();
		System.out.println(header);
		System.out.println(status);
		System.out.println("qui ci siamo");
		//System.out.println(sessionDTO.block().getBody().getCodServizio());
		// messo qua per debug
		//		Mono<AppSessionResponse> response = sessionDTO.flatMap(resp -> ((ClientResponse) resp).bodyToMono(AppSessionResponse.class));
		System.out.println(sessionDTO.share().block());

		return new ResponseEntity<>(sessionDTO.share().block().getBody(), header, status);

	}
	
	// testare questa
	public ResponseEntity<AppSessionResponse> getSessionSec3(AppSessionRequest request,String secSessionId, String appName) {

		
		Mono<ResponseEntity<AppSessionResponse>> sessionDTO = webClient.post()
				.uri("/app/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// setto header da passare al momento
				.header("SEC_SESSION", secSessionId)
				.header("APP_NAME", appName)
				.body(Mono.just(request), AppSessionRequest.class)
				.retrieve()
				.onStatus(HttpStatus:: is4xxClientError, response -> Mono.error( new com.gwsm0.error.handler.BaseActionException("GWSM0-APP-SESSION-ERROR", response.statusCode())))
				
							
				.toEntity(AppSessionResponse.class);
		
		HttpHeaders header = sessionDTO.block().getHeaders();
		HttpStatus status = sessionDTO.block().getStatusCode();
		System.out.println(header);
		System.out.println(status);
		System.out.println("qui ci siamo");
		//System.out.println(sessionDTO.block().getBody().getCodServizio());
		// messo qua per debug
//		Mono<AppSessionResponse> response = sessionDTO.flatMap(resp -> ((ClientResponse) resp).bodyToMono(AppSessionResponse.class));
		System.out.println(sessionDTO.share().block());
		return sessionDTO.block();		
		//return new ResponseEntity<>(sessionDTO.share().block().getBody(), header, status);
				
	}
	
}

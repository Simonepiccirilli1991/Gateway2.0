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


import reactor.core.publisher.Mono;

@Service
public class AppSessionFrag {

	WebClient webClient = WebClient.create("http://localhost:8083");
	
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
	
	public ResponseEntity<AppSessionResponse> getSessionSec(AppSessionRequest request,String secSessionId, String appName) {

		
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
		
		return new ResponseEntity<>(sessionDTO.share().block(),((ResponseSpec) sessionDTO).toBodilessEntity().block().getStatusCode());
				
	}
	
	public ResponseEntity<AppSessionResponse> getSessionSecHeader(AppSessionRequest request,String secSessionId, String appName) {


		// a differenza di altra chiamata ho bisogno di fare una retrive degli header,
		// quindi anziche usare il metodo retrive uso exchange
		ClientResponse sessionDTO = webClient.post()
				.uri("/app/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// setto header da passare al momento
				.header("SEC_SESSION", secSessionId)
				.header("APP_NAME", appName)
				.body(Mono.just(request), AppSessionRequest.class)
				.exchange().block();
		// e deprecato, vediamo se funziona
		HttpHeaders header = sessionDTO.headers().asHttpHeaders();
		HttpStatus status = sessionDTO.statusCode();

		//				

		return new ResponseEntity<>(sessionDTO.bodyToMono(AppSessionResponse.class).share().block(), header, status);

	}
	
	// testare questa
	public ResponseEntity<AppSessionResponse> getSessionSec3(AppSessionRequest request,String secSessionId, String appName) {

		
		// a differenza di altra chiamata ho bisogno di fare una retrive degli header,
		// quindi anziche usare il metodo retrive uso exchange
		Mono<ResponseEntity<AppSessionResponse>> sessionDTO = webClient.post()
				.uri("/app/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// setto header da passare al momento
				.header("SEC_SESSION", secSessionId)
				.header("APP_NAME", appName)
				.body(Mono.just(request), AppSessionRequest.class)
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
	
}

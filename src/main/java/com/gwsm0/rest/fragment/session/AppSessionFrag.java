package com.gwsm0.rest.fragment.session;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gwsm0.error.handler.BaseErrorException;
import com.gwsm0.fragment.model.session.AppSessionRequest;
import com.gwsm0.fragment.model.session.AppSessionResponse;
import com.gwsm0.model.request.LogoutRequest;
import com.gwsm0.model.response.LogoutResponse;
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
	
	// workama al top 
	public ResponseEntity<AppSessionResponse> getSessionApp(AppSessionRequest request,String secSessionId, String appName) {

		Mono<ResponseEntity<AppSessionResponse>> sessionDTO = webClient.post()
				.uri("/app/create")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// setto header da passare al momento
				.header("SEC_SESSION", secSessionId)
				.header("APP_NAME", appName)
				.body(Mono.justOrEmpty(request), AppSessionRequest.class)
				.retrieve()
				// gestione generica per tutti i 400
				.onStatus(HttpStatus:: is4xxClientError, response -> {
					//TODO capire come prendere sta minchia di parametro dalla response anche s ein errore
					throw new BaseErrorException(response.statusCode(), "blabla");
					//return Mono.error(new BaseErrorException(HttpStatus.CONFLICT, "prova90"));
				})
				.onStatus(HttpStatus::is5xxServerError, response ->{
					throw new BaseErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "GENERIC-ERORR-GWSM0");
				})
				.toEntity(AppSessionResponse.class);
		
		ResponseEntity<AppSessionResponse> response = sessionDTO.block();
		//NOTA BENE OGNI VOLTA CHE USI BLOCK FA SUBSCRIPTION QUINDI CHIUDE CHIAMATA,
		// VA PASSATO SU DTO SE NO FA PIU CHIAMATE A L?API TANTI QUANTI SONO i BLOCK()
		System.out.println(response.getHeaders());
		System.out.println(response.getStatusCode());


		return new ResponseEntity<>(response.getBody(),response.getHeaders(),response.getStatusCode());

	}
	
	// workama al top , non gestisce errori al momento
		public ResponseEntity<AppSessionResponse> getSessionAppAdnError(AppSessionRequest request,String secSessionId, String appName) {

			Mono<ResponseEntity<AppSessionResponse>> sessionDTO = webClient.post()
					.uri("/app/create")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					// setto header da passare al momento
					.header("SEC_SESSION", secSessionId)
					.header("APP_NAME", appName)
					.body(Mono.justOrEmpty(request), AppSessionRequest.class)
					.retrieve()
					// gestione generica per tutti i 400
					.onStatus(HttpStatus:: is4xxClientError, response -> {
						response.bodyToMono(AppSessionResponse.class)
						.handle((error,sink) ->
						
						sink.error(new BaseErrorException(response.statusCode(),error.getCodServizio())));
						throw new BaseErrorException(response.statusCode(), "da rimpeire poi");
					
					})
					.onStatus(HttpStatus::is5xxServerError, response ->{
						throw new BaseErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "GENERIC-ERORR-GWSM0");
					})
					.toEntity(AppSessionResponse.class);

			ResponseEntity<AppSessionResponse> response = sessionDTO.block();
			//NOTA BENE OGNI VOLTA CHE USI BLOCK FA SUBSCRIPTION QUINDI CHIUDE CHIAMATA,
			// VA PASSATO SU DTO SE NO FA PIU CHIAMATE A L?API TANTI QUANTI SONO i BLOCK()
			System.out.println(response.getHeaders());
			System.out.println(response.getStatusCode());


			return new ResponseEntity<>(response.getBody(),response.getHeaders(),response.getStatusCode());

		}
		
		
		//logout 
		public ResponseEntity<LogoutResponse> logout(String bt, String username, String appSession,String secSession){

			// monto request qui per in vista di futuri arricchimenti
			LogoutRequest request = new LogoutRequest();
			request.setBt(bt);
			request.setAppSecId(appSession);

			// chiamata a service
			Mono<ResponseEntity<LogoutResponse>> logoutDTO = webClient.post()
					.uri("/app/logout")
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					// setto header da passare al momento
					.header("SEC_SESSION", secSession)
					.header("APP_NAME", "mocked")
					.body(Mono.justOrEmpty(request), LogoutRequest.class)
					.retrieve()
					// gestione generica per tutti i 400
					.onStatus(HttpStatus:: is4xxClientError, response -> {
						//TODO capire come prendere sta minchia di parametro dalla response anche s ein errore
						throw new BaseErrorException(response.statusCode(), "blabla");
						//return Mono.error(new BaseErrorException(HttpStatus.CONFLICT, "prova90"));
					})
					.onStatus(HttpStatus::is5xxServerError, response ->{
						throw new BaseErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "GENERIC-ERORR-GWSM0");
					})
					.toEntity(LogoutResponse.class);

			ResponseEntity<LogoutResponse> response = logoutDTO.block();

			System.out.println(response.getHeaders());
			System.out.println(response.getStatusCode());


			return new ResponseEntity<>(response.getBody(),response.getHeaders(),response.getStatusCode());
		}
	
}

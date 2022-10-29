package com.gwsm0.rest.fragment.document;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.lowagie.text.pdf.PdfReader;


@Service
public class DocumentFragment {

	
	
	RestTemplate restTemplate = new RestTemplate();
	
	public String docToString() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<byte[]> result =
				restTemplate.exchange("http://localhost:8080/docv0/get", HttpMethod.GET, entity, byte[].class);


		byte[] content = result.getBody();
		Files.write(Paths.get("C:\\tmp\\ajeje.pdf"), content, StandardOpenOption.CREATE );

		PdfReader reader = new PdfReader((InputStream) Paths.get("C:\\tmp\\prova.pdf"));
		
		// TODO ho cambiato libreria ed e esplosa
		//String response = PdfTextExtractor.getTextFromPage(reader, 1);
		String response = "ciao ho cambiato librearia e ora non funziono";
		return response;

	}
	// getPdf
	public byte[] getPdfByte(String nomePdf) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<byte[]> result =
				restTemplate.exchange("http://localhost:8080/docv0/getPdf", HttpMethod.GET, entity, byte[].class);


		byte[] content = result.getBody();

		return content;
	}
	
	
}

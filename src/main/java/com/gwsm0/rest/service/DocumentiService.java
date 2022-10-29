package com.gwsm0.rest.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwsm0.model.request.DocumentRequest;
import com.gwsm0.model.response.DocumentResponse;
import com.gwsm0.rest.fragment.document.DocumentFragment;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;


@Service
public class DocumentiService {

	@Autowired
	private DocumentFragment docFrag;
	
	// salva documento e torna stringa
	public String getDocument() throws Exception {
		
		// chiamo la getdOCUMENT AL MOMENTO torna solo pdf classico
		// voglio salvarlo qui e tornare stringa il contenuto.
		String resp = docFrag.docToString();
		
		return resp;
	}
	// getPdf, prendo pdf come byte array e me lo ricostruisco qui
	// per controllo acrofiled
	public List<DocumentResponse> controllaFirme(DocumentRequest request) throws IOException{
		
		byte[] byteResp = docFrag.getPdfByte(request.getDocumentName());
		
		// ricreo pdf e poi dedico cosa farne
		ByteArrayInputStream inStream = new ByteArrayInputStream(byteResp);
	    PdfReader reader = new PdfReader(inStream);
	    
	    return controlloFirme(reader);
	}
	
	
	private List<DocumentResponse> controlloFirme(PdfReader pdf){
		
		AcroFields fields = pdf.getAcroFields();
		Set<String> fldNames = fields.getAllFields().keySet();
		List<DocumentResponse> response = new ArrayList<>();
		// messa qui per test
		int i = 1;
		for (String fldName : fldNames) {
			//dto dove salvare dati nel ciclo
			DocumentResponse acroDTO = new DocumentResponse();
			System.out.println( fldName + ": " + fields.getField( fldName ) );
			acroDTO.setDocumentId(fldName);
			System.out.println("ciclo "+i+":" + fldName);
			acroDTO.setSignature(fields.getField( fldName ));
			response.add(acroDTO);
			i++;
		}
		  return response;
	}
	
	
	
}

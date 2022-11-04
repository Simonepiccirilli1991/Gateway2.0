package com.gwsm0.rest.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gwsm0.model.request.DocumentRequest;
import com.gwsm0.model.response.DocumentResponse;
import com.gwsm0.rest.fragment.document.DocumentFragment;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfPKCS7;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfString;


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
	// best metod , usare questo
	public List<DocumentResponse> controllaFirme(DocumentRequest request) throws IOException{

		byte[] byteResp = docFrag.getPdfByte(request.getDocumentName());

		// ricreo pdf e poi dedico cosa farne
		ByteArrayInputStream inStream = new ByteArrayInputStream(byteResp);
		PdfReader reader = new PdfReader(inStream);

		return controlloFirme(reader);
	}
	
	// controlla solo acrofield non firmati
	public List<DocumentResponse> controllaFirmeVuote(DocumentRequest request) throws IOException{

		byte[] byteResp = docFrag.getPdfByte(request.getDocumentName());

		// ricreo pdf e poi dedico cosa farne
		ByteArrayInputStream inStream = new ByteArrayInputStream(byteResp);
		PdfReader reader = new PdfReader(inStream);

		return controlloFirmeVuote(reader);
	}

	// metodo nuovo 
	public List<DocumentResponse> controllaFirme1(DocumentRequest request) throws IOException{

		byte[] byteResp = docFrag.getPdfByte(request.getDocumentName());

		// ricreo pdf e poi dedico cosa farne


		return controlloFirmeAll(byteResp);
	}

	public List<DocumentResponse> controllaFirmeNew(DocumentRequest request) throws IOException{

		byte[] byteResp = docFrag.getPdfByte(request.getDocumentName());

		// ricreo pdf e poi dedico cosa farne

		return controllaFirmaNew(byteResp);
	}

	// al momento worka da paura
	private List<DocumentResponse> controlloFirme(PdfReader pdf){

		AcroFields fields = pdf.getAcroFields();
		List<DocumentResponse> response = new ArrayList<>();
		List<String> listaFirme = fields.getSignedFieldNames();
		System.out.println(listaFirme);
		for(String listaFirmes : listaFirme) {

			DocumentResponse docDto = new DocumentResponse();
			String firmante = "";

			int revision = pdf.getAcroFields().getRevision(listaFirmes);

			AcroFields.Item item = fields.getFieldItem(listaFirmes);
			PdfDictionary d = item.getMerged(0);
			PdfDictionary v = d.getAsDict(PdfName.V);

			if (v != null) {
				PdfString name = v.getAsString(PdfName.NAME);
				//System.out.println("Rev."+revision+": Signed by "+ name.toString());
				if(!ObjectUtils.isEmpty(name))
					firmante = name.toString();
			}

			if(ObjectUtils.isEmpty(firmante))
				continue;
			else {
				docDto.setDocumentId(listaFirmes.substring(listaFirmes.lastIndexOf("-") + 1));
				docDto.setSignature(firmante);
				response.add(docDto);
			}
			System.out.println(firmante);
		}


		return response;
	}
	
	private List<DocumentResponse> controlloFirmeVuote(PdfReader pdf){

		AcroFields fields = pdf.getAcroFields();
		List<DocumentResponse> response = new ArrayList<>();
		List<String> listaFirme = fields.getFieldNamesWithBlankSignatures();
		System.out.println(listaFirme);
		for(String listaFirmes : listaFirme) {

			DocumentResponse docDto = new DocumentResponse();

			docDto.setDocumentId(listaFirmes.substring(listaFirmes.lastIndexOf("-") + 1));

			response.add(docDto);
		}
		return response;
	}
	
	
	private List<DocumentResponse> controlloFirmeOld(PdfReader pdf){

		AcroFields fields = pdf.getAcroFields();
		Set<String> fldNames = fields.getAllFields().keySet();
		List<DocumentResponse> response = new ArrayList<>();

		// messa qui per test
		int i = 1;
		List<String> listaFirme = fields.getSignedFieldNames();

		// torna tutti acrofield, anche quelli non firmati/ non torna valore firme
		for (String fldName : fldNames) {
			//dto dove salvare dati nel ciclo
			DocumentResponse acroDTO = new DocumentResponse();
			acroDTO.setDocumentId(fldName);
			System.out.println("ciclo "+i+" signature :" + fldName +" firma:");
			acroDTO.setSignature(fields.getField( fldName ));
			response.add(acroDTO);
			i++;
		}
		return response;
	}

	//TODO worka , ma torna per tutti finire di rivere ciclo
	private List<DocumentResponse> controlloFirmeAll(byte[] req) throws IOException{

		PDDocument document = PDDocument.load(new ByteArrayInputStream(req));
		List<DocumentResponse> response = new ArrayList<>();
		for (PDSignatureField docId : document.getSignatureFields()) {

			for (PDSignature sig : document.getSignatureDictionaries()) {

				DocumentResponse resp = new DocumentResponse();

				COSDictionary sigDict = sig.getCOSObject();

				resp.setDocumentId(docId.getFullyQualifiedName());
				resp.setSignature(sig.getName());
				response.add(resp);
			}

		}
		return response;
	}


	// prova metodo nuovo funziona
	private List<DocumentResponse> controllaFirmaNew(byte[] req) throws IOException{

		List<DocumentResponse> response = new ArrayList<>();
		
		PDDocument docum = PDDocument.load(req);
		
		ByteArrayInputStream inStream = new ByteArrayInputStream(req);
		PdfReader reader = new PdfReader(inStream);
		AcroFields acroFields = reader.getAcroFields();
		List<String> signatureNames = acroFields.getSignedFieldNames();
		int i = signatureNames.size();
		for(String name : signatureNames)
		{
			int o = 0;
			PDSignature sig =  docum.getSignatureDictionaries().get(o);
			DocumentResponse doc = new DocumentResponse();
			System.out.println(name);
			System.out.println(sig.getName());
			doc.setSignature(sig.getName());
			doc.setDocumentId(name);
			response.add(doc);
			if( o < i)
				i++;
		}



		return response;
	}

}

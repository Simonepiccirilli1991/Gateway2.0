package com.gwsm0.rest.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwsm0.model.request.DocumentRequest;
import com.gwsm0.model.response.DocumentResponse;
import com.gwsm0.rest.fragment.document.DocumentFragment;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.CertificateInfo;
import com.itextpdf.signatures.PdfPKCS7;
import com.itextpdf.signatures.SignaturePermissions;
import com.itextpdf.signatures.SignatureUtil;


@Service
public class ItextService {

	@Autowired
	private DocumentFragment docFrag;
	
	public List<DocumentResponse> controllaFirme(DocumentRequest request) throws IOException{
		
		byte[] byteResp = docFrag.getPdfByte(request.getDocumentName());
		
		// ricreo pdf e poi dedico cosa farne
		ByteArrayInputStream inStream = new ByteArrayInputStream(byteResp);
	    PdfReader reader = new PdfReader(inStream);
	    
	    return infoFirme(reader);
	}
	
	private List<DocumentResponse> infoFirme(PdfReader pdf){
		
		
		PdfDocument pdfDoc = new PdfDocument(pdf);
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, false);
        SignaturePermissions perms = null;
        SignatureUtil signUtil = new SignatureUtil(pdfDoc);
        List<String> names = signUtil.getSignatureNames();
        
        System.out.println(names);
        for (String name : names) {
            System.out.println("===== " + name + " =====");
            PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);
            X509Certificate cert = (X509Certificate) pkcs7.getSigningCertificate();
            System.out.println("Name of the signer: " + CertificateInfo.getSubjectFields(cert).getField("CN"));
            if (pkcs7.getSignName() != null) {
                System.out.println("Alternative name of the signer: " + pkcs7.getSignName());
            }
        
        }
		
		return null;
	}
}

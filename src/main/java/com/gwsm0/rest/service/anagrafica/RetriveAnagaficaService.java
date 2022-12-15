package com.gwsm0.rest.service.anagrafica;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.fragment.model.wiam.AnagraficaWResponse;
import com.gwsm0.model.response.AnagraficaResponse;
import com.gwsm0.rest.fragment.wiam.AnagraficaWiam;

@Service
public class RetriveAnagaficaService {

	@Autowired
	AnagraficaWiam anagWiam;
	
	
	public AnagraficaResponse retriveCodiceConto(String tiporicerca, String value, String cognome) {
		
		AnagraficaResponse response = new AnagraficaResponse();		
		
		AnagraficaWResponse wiamResp  = anagWiam.getAllAnagbyNameoCf(tiporicerca, value);
		
		boolean cellcognome = false;
		
		Optional<AnagraficaWRequest> anag = wiamResp.getListaAnagrafica().stream().filter(resp -> resp.getCognome().equals(cognome)).findAny();
		
		if(!anag.isEmpty())
			cellcognome = true;
		
		if(cellcognome)
			response.setAnagrafica(anag.get());
		
		return response;
	}
}

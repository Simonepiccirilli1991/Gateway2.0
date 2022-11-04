package com.gwsm0.model.response;

import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.model.base.BaseActionResponse;

public class AnagraficaResponse extends BaseActionResponse{

	private AnagraficaWRequest Anagrafica;

	public AnagraficaWRequest getAnagrafica() {
		return Anagrafica;
	}

	public void setAnagrafica(AnagraficaWRequest anagrafica) {
		Anagrafica = anagrafica;
	}
	
	
	
	
}

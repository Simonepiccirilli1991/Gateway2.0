package com.gwsm0.model.request;

import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.model.base.BaseActionRequest;

public class AnagraficaRequest extends BaseActionRequest{

	private AnagraficaWRequest anagrafica;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AnagraficaWRequest getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(AnagraficaWRequest anagrafica) {
		this.anagrafica = anagrafica;
	}
	
	
	
	
}

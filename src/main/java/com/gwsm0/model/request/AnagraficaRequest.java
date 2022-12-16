package com.gwsm0.model.request;

import com.gwsm0.fragment.model.wiam.AnagraficaWRequest;
import com.gwsm0.model.base.BaseActionRequest;

public class AnagraficaRequest extends BaseActionRequest{

	private AnagraficaWRequest anagrafica;
	
	private String username;
	//usate in retrive
	private String tiporicerca;
	private String value;

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

	public String getTiporicerca() {
		return tiporicerca;
	}

	public void setTiporicerca(String tiporicerca) {
		this.tiporicerca = tiporicerca;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}

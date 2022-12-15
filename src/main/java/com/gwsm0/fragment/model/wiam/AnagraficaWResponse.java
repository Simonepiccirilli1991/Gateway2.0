package com.gwsm0.fragment.model.wiam;

import java.util.List;

public class AnagraficaWResponse extends BaseWiamResponse{

	
	private AnagraficaWRequest Anagrafica;
	
	private List<AnagraficaWRequest> listaAnagrafica;
	
	

	public List<AnagraficaWRequest> getListaAnagrafica() {
		return listaAnagrafica;
	}

	public void setListaAnagrafica(List<AnagraficaWRequest> listaAnagrafica) {
		this.listaAnagrafica = listaAnagrafica;
	}

	public AnagraficaWRequest getAnagrafica() {
		return Anagrafica;
	}

	public void setAnagrafica(AnagraficaWRequest anagrafica) {
		Anagrafica = anagrafica;
	}

	@Override
	public String toString() {
		return "AnagraficaWResponse [Anagrafica=" + Anagrafica + "]";
	}	
	
	
	
}

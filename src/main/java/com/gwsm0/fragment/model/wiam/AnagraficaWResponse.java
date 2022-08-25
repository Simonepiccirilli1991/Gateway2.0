package com.gwsm0.fragment.model.wiam;

public class AnagraficaWResponse extends BaseWiamResponse{

	
	private AnagraficaWRequest Anagrafica;

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

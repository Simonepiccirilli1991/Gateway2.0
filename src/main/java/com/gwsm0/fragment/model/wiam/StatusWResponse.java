package com.gwsm0.fragment.model.wiam;

import lombok.Data;

@Data
public class StatusWResponse extends BaseWiamResponse{

	private boolean utenteRegistrato;
	private boolean anagragicaP;
	private boolean enforced;
	private boolean sicuriro;
	private boolean ricordami;
	
	// getter e setter perche eclipse di merda su mac non scarica lombock
	
	public boolean isUtenteRegistrato() {
		return utenteRegistrato;
	}
	public void setUtenteRegistrato(boolean utenteRegistrato) {
		this.utenteRegistrato = utenteRegistrato;
	}
	public boolean isAnagragicaP() {
		return anagragicaP;
	}
	public void setAnagragicaP(boolean anagragicaP) {
		this.anagragicaP = anagragicaP;
	}
	public boolean isEnforced() {
		return enforced;
	}
	public void setEnforced(boolean enforced) {
		this.enforced = enforced;
	}
	public boolean isSicuriro() {
		return sicuriro;
	}
	public void setSicuriro(boolean sicuriro) {
		this.sicuriro = sicuriro;
	}
	public boolean isRicordami() {
		return ricordami;
	}
	public void setRicordami(boolean ricordami) {
		this.ricordami = ricordami;
	}
	
	
	
	
	
	
	
}

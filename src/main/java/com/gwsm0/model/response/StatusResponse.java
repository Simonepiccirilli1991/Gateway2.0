package com.gwsm0.model.response;

import com.gwsm0.model.base.BaseActionResponse;

public class StatusResponse extends BaseActionResponse{

	private boolean utenteRegistrato;
	private boolean anagragicaP;
	private boolean enforced;
	private boolean sicuriro;
	
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
	
	
}

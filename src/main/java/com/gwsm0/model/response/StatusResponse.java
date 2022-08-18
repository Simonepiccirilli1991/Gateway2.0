package com.gwsm0.model.response;

import com.gwsm0.model.base.BaseActionResponse;

public class StatusResponse extends BaseActionResponse{

	private Boolean utenteRegistrato;
	private Boolean anagragicaP;
	private Boolean enforced;
	private Boolean sicuriro;
	
	public Boolean getUtenteRegistrato() {
		return utenteRegistrato;
	}
	public void setUtenteRegistrato(Boolean utenteRegistrato) {
		this.utenteRegistrato = utenteRegistrato;
	}
	public Boolean getAnagragicaP() {
		return anagragicaP;
	}
	public void setAnagragicaP(Boolean anagragicaP) {
		this.anagragicaP = anagragicaP;
	}
	public Boolean getEnforced() {
		return enforced;
	}
	public void setEnforced(Boolean enforced) {
		this.enforced = enforced;
	}
	public Boolean getSicuriro() {
		return sicuriro;
	}
	public void setSicuriro(Boolean sicuriro) {
		this.sicuriro = sicuriro;
	}
	
	
	
	
	
	
}

package com.gwsm0.fragment.model.wiam;

public class EnforcementWRequest {

	private String username;
	private String abiSottoscrizione;
	private String tipoSicurezza;
	private Boolean enforced;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAbiSottoscrizione() {
		return abiSottoscrizione;
	}
	public void setAbiSottoscrizione(String abiSottoscrizione) {
		this.abiSottoscrizione = abiSottoscrizione;
	}
	public String getTipoSicurezza() {
		return tipoSicurezza;
	}
	public void setTipoSicurezza(String tipoSicurezza) {
		this.tipoSicurezza = tipoSicurezza;
	}
	public Boolean getEnforced() {
		return enforced;
	}
	public void setEnforced(Boolean enforced) {
		this.enforced = enforced;
	}
	
	
}

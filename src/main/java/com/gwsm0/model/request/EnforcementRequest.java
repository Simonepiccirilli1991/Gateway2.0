package com.gwsm0.model.request;

public class EnforcementRequest {

	private String username;
	private String abiSottoscrizione;
	private String otp;
	private boolean ricordami;
	
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public boolean isRicordami() {
		return ricordami;
	}
	public void setRicordami(boolean ricordami) {
		this.ricordami = ricordami;
	}
	
	
}

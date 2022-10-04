package com.gwsm0.fragment.model.session;

public class AppSessionResponse {

	private String codServizio;
	private String appSessionId;
	private Boolean alreadyActive;
	private Boolean active;
	
	public String getCodServizio() {
		return codServizio;
	}
	public void setCodServizio(String codServizio) {
		this.codServizio = codServizio;
	}
	public String getAppSessionId() {
		return appSessionId;
	}
	public void setAppSessionId(String appSessionId) {
		this.appSessionId = appSessionId;
	}
	public Boolean getAlreadyActive() {
		return alreadyActive;
	}
	public void setAlreadyActive(Boolean alreadyActive) {
		this.alreadyActive = alreadyActive;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}

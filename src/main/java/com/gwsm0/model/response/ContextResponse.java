package com.gwsm0.model.response;

public class ContextResponse {

	private String secSessId;
	private String appSecId;
	private Boolean created;
	private Boolean alreadyActive;
	
	public String getSecSessId() {
		return secSessId;
	}
	public void setSecSessId(String secSessId) {
		this.secSessId = secSessId;
	}
	public String getAppSecId() {
		return appSecId;
	}
	public void setAppSecId(String appSecId) {
		this.appSecId = appSecId;
	}
	public Boolean getCreated() {
		return created;
	}
	public void setCreated(Boolean created) {
		this.created = created;
	}
	public Boolean getAlreadyActive() {
		return alreadyActive;
	}
	public void setAlreadyActive(Boolean alreadyActive) {
		this.alreadyActive = alreadyActive;
	}
	
	
}

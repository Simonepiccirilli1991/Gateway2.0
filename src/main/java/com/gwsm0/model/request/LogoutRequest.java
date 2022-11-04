package com.gwsm0.model.request;

public class LogoutRequest {

	
	private String bt;
	private String username;
	private String appSecId;
	private String secSessId;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAppSecId() {
		return appSecId;
	}
	public void setAppSecId(String appSecId) {
		this.appSecId = appSecId;
	}
	public String getSecSessId() {
		return secSessId;
	}
	public void setSecSessId(String secSessId) {
		this.secSessId = secSessId;
	}
	
	
}

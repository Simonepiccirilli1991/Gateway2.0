package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionRequest;

public class StatusRequest extends BaseActionRequest{

	private String username;
	private String bt;
	private String canale;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getCanale() {
		return canale;
	}
	public void setCanale(String canale) {
		this.canale = canale;
	}
	
	
}

package com.gwsm0.model.base;

import lombok.Data;

@Data
public class Session {

	
	private String bt;
	private String bd;
	private String abiSottoscrizione;
	private String canale;
	private int actionId;
	private String sessionId;
	
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getBd() {
		return bd;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	public String getAbiSottoscrizione() {
		return abiSottoscrizione;
	}
	public void setAbiSottoscrizione(String abiSottoscrizione) {
		this.abiSottoscrizione = abiSottoscrizione;
	}
	public String getCanale() {
		return canale;
	}
	public void setCanale(String canale) {
		this.canale = canale;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
	
}

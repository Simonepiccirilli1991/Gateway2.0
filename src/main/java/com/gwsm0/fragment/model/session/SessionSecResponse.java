package com.gwsm0.fragment.model.session;

public class SessionSecResponse {

	private String username;
	private String bt;
	private String canale;
	private String geneTime;
	private String scope;
	private String abi;
	private String sessionId;
	private String updateTime;
	private boolean sessionActive;
	private Boolean sessionAlreadyExist;
	private Boolean sessionNoExist;
	
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
	public String getGeneTime() {
		return geneTime;
	}
	public void setGeneTime(String geneTime) {
		this.geneTime = geneTime;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAbi() {
		return abi;
	}
	public void setAbi(String abi) {
		this.abi = abi;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isSessionActive() {
		return sessionActive;
	}
	public void setSessionActive(boolean sessionActive) {
		this.sessionActive = sessionActive;
	}
	public Boolean getSessionAlreadyExist() {
		return sessionAlreadyExist;
	}
	public void setSessionAlreadyExist(Boolean sessionAlreadyExist) {
		this.sessionAlreadyExist = sessionAlreadyExist;
	}
	public Boolean getSessionNoExist() {
		return sessionNoExist;
	}
	public void setSessionNoExist(Boolean sessionNoExist) {
		this.sessionNoExist = sessionNoExist;
	}
	
	
}

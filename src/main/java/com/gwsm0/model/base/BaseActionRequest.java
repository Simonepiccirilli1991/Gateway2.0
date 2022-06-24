package com.gwsm0.model.base;

import com.gwsm0.constants.ActionConstants;

public class BaseActionRequest{

	private SessionData sessionData;
	private ActionConstants action;
	private String sessionId;
	
	public SessionData getSessionData() {
		return sessionData;
	}
	public void setSessionData(SessionData sessionData) {
		this.sessionData = sessionData;
	}
	public ActionConstants getAction() {
		return action;
	}
	public void setAction(ActionConstants action) {
		this.action = action;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
}

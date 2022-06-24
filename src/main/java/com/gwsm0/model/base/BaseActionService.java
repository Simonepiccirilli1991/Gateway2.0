package com.gwsm0.model.base;

import org.springframework.http.HttpHeaders;

public abstract class BaseActionService <I extends BaseActionRequest, O extends BaseActionResponse>{

	protected final String className;

	protected BaseActionService(){ this.className = null; }
	protected BaseActionService(String className) { this.className = className; }

	public O call(I iRequest, HttpHeaders httpHeaders)  {
		O oResponse = call_(iRequest, httpHeaders);
		return oResponse;
	}

	public abstract O call_(I iRequest, HttpHeaders httpHeaders);
	
}

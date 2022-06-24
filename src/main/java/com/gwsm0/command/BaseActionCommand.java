package com.gwsm0.command;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.gwsm0.constants.ActErrors;
import com.gwsm0.error.handler.BaseActionException;
import com.gwsm0.model.base.BaseActionRequest;
import com.gwsm0.model.base.BaseActionResponse;
import com.gwsm0.model.base.BaseActionService;

public class BaseActionCommand <I extends BaseActionRequest, O extends BaseActionResponse>{

	@Autowired
	private BeanFactory beanFactory;
	protected I iRequest;
	protected HttpHeaders httpHeaders;
	
	public BaseActionCommand(I iRequest, HttpHeaders httpHeaders) {
		this.iRequest = iRequest;
		this.httpHeaders = httpHeaders;
	}
	
	protected boolean canExecute() {
		return this.iRequest != null;
	}
	
	public final <T extends BaseActionService<I, O>> O getResponse(Class<T> serviceClass){
		
		return beanFactory.getBean(serviceClass).call(iRequest, httpHeaders);
	}
	
	public static void throwInvalidAction() 	throws BaseActionException { throw new BaseActionException(ActErrors.INVALIDACTION);  }
	public static void throwInvalidRequest() 	throws BaseActionException { throw new BaseActionException(ActErrors.INVALIDREQUEST); }
	public final O throwDefault() 				throws BaseActionException { throw new BaseActionException(ActErrors.DEFAULTACTION);  }
}

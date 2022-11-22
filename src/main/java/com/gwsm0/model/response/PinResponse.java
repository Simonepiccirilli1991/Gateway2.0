package com.gwsm0.model.response;

import com.gwsm0.model.base.BaseActionResponse;

import lombok.Data;

@Data
public class PinResponse extends BaseActionResponse{

	private boolean pinOk;
	private boolean checkDataNascita;
	private Boolean pinChanged;
	
	
}

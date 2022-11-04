package com.gwsm0.model.response;

import com.gwsm0.model.base.BaseActionResponse;

public class PinResponse extends BaseActionResponse{

	private boolean utenteReg;
	private boolean pinOk;
	private boolean check;
	
	public boolean isUtenteReg() {
		return utenteReg;
	}
	public void setUtenteReg(boolean utenteReg) {
		this.utenteReg = utenteReg;
	}
	public boolean isPinOk() {
		return pinOk;
	}
	public void setPinOk(boolean pinOk) {
		this.pinOk = pinOk;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
}

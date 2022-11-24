package com.gwsm0.model.response;

import com.gwsm0.model.base.BaseActionResponse;

import lombok.Data;

@Data
public class PinResponse extends BaseActionResponse{

	private boolean pinOk;
	private boolean checkDataNascita;
	private Boolean pinChanged;
	
	// getter e setter perche eclipse di merda su mac non scarica lombock
	
	public boolean isPinOk() {
		return pinOk;
	}
	public void setPinOk(boolean pinOk) {
		this.pinOk = pinOk;
	}
	public boolean isCheckDataNascita() {
		return checkDataNascita;
	}
	public void setCheckDataNascita(boolean checkDataNascita) {
		this.checkDataNascita = checkDataNascita;
	}
	public Boolean getPinChanged() {
		return pinChanged;
	}
	public void setPinChanged(Boolean pinChanged) {
		this.pinChanged = pinChanged;
	}
	
	
	
}

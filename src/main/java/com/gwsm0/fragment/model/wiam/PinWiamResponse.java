package com.gwsm0.fragment.model.wiam;

import lombok.Data;

@Data
public class PinWiamResponse extends BaseWiamResponse{

	private Boolean pinChanged;

	public Boolean getPinChanged() {
		return pinChanged;
	}

	public void setPinChanged(Boolean pinChanged) {
		this.pinChanged = pinChanged;
	}
	
	
}

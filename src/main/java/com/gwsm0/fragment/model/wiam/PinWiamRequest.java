package com.gwsm0.fragment.model.wiam;

import lombok.Data;

@Data
public class PinWiamRequest {

	private String bt;
	private String canale;
	private String pin;
	private String oldPin;
	private String cf;
	private String username;
}

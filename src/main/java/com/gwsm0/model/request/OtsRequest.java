package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionRequest;

public class OtsRequest extends BaseActionRequest{

	private String ots;
	private String trxId;
	private String email;
	private String bt;
	
	public String getOts() {
		return ots;
	}
	public void setOts(String ots) {
		this.ots = ots;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	
	
}

package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionRequest;

import lombok.Data;

@Data
public class OtpRequest extends BaseActionRequest{

	private String ots;
	private String email;
	private String bt;
	private String profile;
	private String trxId;
	
	// getter e setter perche eclipse di merda su mac non scarica lombock
	
	public String getOts() {
		return ots;
	}
	public void setOts(String ots) {
		this.ots = ots;
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	
	
}

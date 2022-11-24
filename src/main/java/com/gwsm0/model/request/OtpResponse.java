package com.gwsm0.model.request;

import com.gwsm0.model.base.BaseActionResponse;

import lombok.Data;

@Data
public class OtpResponse extends BaseActionResponse{

	private String trxId;
	private Boolean sended;
	private Boolean phone;
	private Boolean mail;
	private String codiceEsito;
	private Boolean checkOk;
	
	// getter e setter perche eclipse di merda su mac non scarica lombock
	
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	public Boolean getSended() {
		return sended;
	}
	public void setSended(Boolean sended) {
		this.sended = sended;
	}
	public Boolean getPhone() {
		return phone;
	}
	public void setPhone(Boolean phone) {
		this.phone = phone;
	}
	public Boolean getMail() {
		return mail;
	}
	public void setMail(Boolean mail) {
		this.mail = mail;
	}
	public String getCodiceEsito() {
		return codiceEsito;
	}
	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}
	public Boolean getCheckOk() {
		return checkOk;
	}
	public void setCheckOk(Boolean checkOk) {
		this.checkOk = checkOk;
	}
	
	
	
}

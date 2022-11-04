package com.gwsm0.fragment.model.wiam;



public class BaseWiamResponse {

	private Boolean isError ;
	private String codiceEsito;
	private String errDsc; //= Constants.BaseWiamResponseC.GenericError;

	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	public String getCodiceEsito() {
		return codiceEsito;
	}
	public void setCodiceEsito(String codiceEsito) {
		this.codiceEsito = codiceEsito;
	}
	public String getErrDsc() {
		return errDsc;
	}
	public void setErrDsc(String errDsc) {
		this.errDsc = errDsc;
	}
}

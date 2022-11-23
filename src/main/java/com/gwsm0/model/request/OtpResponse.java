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
}

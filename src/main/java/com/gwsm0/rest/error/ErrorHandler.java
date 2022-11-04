package com.gwsm0.rest.error;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	public static final String LOGIC_PREFIX = "gwsi0.logic.";
	private static final String GENERIC = "gwgp0.generic";
	
	public ResponseEntity<BaseError> actionError(String errorCode){
		
		
		ResponseEntity<BaseError> response = new ResponseEntity<BaseError>(errorMapper(errorCode).getHttpStatus());
		response.getBody().setChiamante(GENERIC);
		response.getBody().setErrorMsg(errorMapper(errorCode).getErrCode());
		response.getBody().setErrorTp(errorCode);
		response.getBody().setHttpS(errorMapper(errorCode).getHttpStatus());
		return response; 
	}
	
	
	
	
	private static ErrorMapperDTO errorMapper(String errId) {
		if(errId == null) errId = "EMPTY";
		String errCode = null;
		String logicErrId = null;
		HttpStatus httpStatus = HttpStatus.FORBIDDEN;

		switch (errId) {
			case "OPERATIONID IS NOT VALID": case "LOGO EXPIRED":
			case "LOGO VALIDATION ERROR":			httpStatus = HttpStatus.UNAUTHORIZED;	logicErrId = "invalidlogo";			break;

			case "ERR008": case "ERR012":													logicErrId = "wrongcontext";		break;

			case "AUKO_93":																	logicErrId = "invalidpinold";		break;
			// ots scaduto - trxOts non valido - Formato ots errato
			case "AUKO_82":	case "AUKO_83":													logicErrId = "blockedotstemp";		break;
			case "AUKO_81":	case "AUKO_85":	case "ERKO_09":									logicErrId = "invalidots";			break;
			case "AUKO_80":																	logicErrId = "expiredots";			break;
			case "AUKO_84":																	logicErrId = "blockedots"; 			break;
			case "AUKO_70":																	logicErrId = "birthday";			break;
			case "AUKO_49": case "AUKO_75":	/** bday */										logicErrId = "invalidotpsms";		break;
			case "AUKO_48":																	logicErrId = "expiredotp";			break;
			case "AUKO_40":																	logicErrId = "inactiveotp";			break;
			case "AUKO_44": case "AUKO_46": case "ERKO_17": case "AUKO_71": case "3007":	logicErrId = "invalidotpsmart";		break;
			case "AUKO_41": case "AUKO_47":													logicErrId = "blockedotp";			break;
			case "AUKO_16":	case "AUKO_19":	/* <= PIN */
			case "AUKO_42": case "AUKO_43": /* <= OTP */									logicErrId = "blockedpinaf";		break;

			default:
			// case "ERKO_02":
				errCode = GENERIC;
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				break;

			
		}

		if(logicErrId != null) errCode = LOGIC_PREFIX + logicErrId;

		return new ErrorMapperDTO(errCode, httpStatus);
	}
}
class ErrorMapperDTO {
	private final String errCode;
	private final HttpStatus httpStatus;
	
	public ErrorMapperDTO(String errCode, HttpStatus httpStatus) {
		this.errCode = errCode;
		this.httpStatus = httpStatus;
	}

	public String getErrCode() { return errCode; }
	public HttpStatus getHttpStatus() { return httpStatus; }
}

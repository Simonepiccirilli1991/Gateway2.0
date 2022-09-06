package com.gwsm0.rest.error;

import java.util.Map;

import org.springframework.http.HttpStatus;



public class BaseActionException extends RuntimeException{

	public static final String LOGIC_PREFIX = "gwgp0.logic.";
	private static final String GENERIC = "gwgp0.generic";

	private static final long serialVersionUID = 1L;
	private String errId;
	
	// TODO finire implementare


		private static ErrorMapperDTO errorMapper(String errId) {
			if(errId == null) errId = "EMPTY";
			String errCode = null;
			String logicErrId = null;
			HttpStatus httpStatus = HttpStatus.FORBIDDEN;
			Map<String, Object> hashMap = null;

			switch (errId) {
				case "OPERATIONID IS NOT VALID": case "LOGO EXPIRED":
				case "LOGO VALIDATION ERROR":													logicErrId = "invalidlogo";			break;

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
				case "AUKO_18":																	logicErrId = "inactivepin";			break;
				case "AUKO_10":																	logicErrId = "invalidnewpinold";	break;
				case "ERKO_16":																	logicErrId = "invalidnewpin";		break;
				case "AUKO_15":																	logicErrId = "invalidnewpinbt";		break;
				case "AUKO_05":																	logicErrId = "blockedpin";			break;
				case "AUKO_03":	case "ERKO_08": 												logicErrId = "invalidpin";			break;

				case "ATKO_01": case "ATKO_02":													logicErrId = "unauthorizeduser";	break;

				case "OVKO_06":																	logicErrId = "blockedpush";			break;
				case "OVKO_10":																	logicErrId = "expiredricordami";	break;
				case "OVKO_12":																	logicErrId = "changedpin";			break;

				// Descrizione strana per essere ciò a cui sono mappati, controllare
				case "INKO_01": case "INKO_02": case "INKO_04":									logicErrId = "invalidricordami";	break;

				case "DSKO_04":																	logicErrId = "blockedds";			break;
				case "DSKO_02":	case "DSKO_03":													logicErrId = "invalidrs";			break;

				case "CPKO_01":																	logicErrId = "blockedphone";		break;

				default:
				// case "ERKO_02":
					errCode = GENERIC;
					httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
					break;

				
			}

			if(logicErrId != null) errCode = LOGIC_PREFIX + logicErrId;

			return new ErrorMapperDTO(errCode, httpStatus, hashMap);
		}
		
		public String getErrId() { return errId; }
		public void setErrId(String errId) { this.errId = errId; }
	}

	// DTO stupido creato per poter passare tre valori da costruttore a costruttore
	class ErrorMapperDTO {
		private final String errCode;
		private final HttpStatus httpStatus;
		private final Map<String, Object> params;
		
		public ErrorMapperDTO(String errCode, HttpStatus httpStatus, Map<String, Object> params) {
			this.errCode = errCode;
			this.httpStatus = httpStatus;
			this.params = params;
		}

		public String getErrCode() { return errCode; }
		public HttpStatus getHttpStatus() { return httpStatus; }
		public Map<String, Object> getParams() { return params; }
}

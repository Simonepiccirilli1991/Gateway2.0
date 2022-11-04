package com.gwsm0.error.handler;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(BaseErrorException.class)
	public ResponseEntity<BaseErrorResponse> handleCustomErrorExceptions(Exception e) {
		// casting the generic Exception e to CustomErrorException
		BaseErrorException customErrorException = (BaseErrorException) e;

		HttpStatus status = customErrorException.getStatus();

		// converting the stack trace to String
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		customErrorException.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		return new ResponseEntity<>(new BaseErrorResponse( status,customErrorException.getMessage(),
				stackTrace, customErrorException.getData()),status);
	}

}

package com.mtqp.test.exception;

public class JsonConvertingException extends MTQPException{

	public JsonConvertingException(String message) {

		super(message);
	}

	public JsonConvertingException(String message, Throwable cause) {

		super(message, cause);
	}

	public JsonConvertingException(Throwable cause) {

		super(cause);
	}
}

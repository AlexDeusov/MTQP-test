package com.mtqp.test.exception;

public class MTQPException extends RuntimeException{

	public MTQPException(String message) {

		this(message, null);
	}

	public MTQPException(String message, Throwable cause) {

		super(message, cause);
	}

	public MTQPException(Throwable cause) {

		this(null, cause);
	}
}

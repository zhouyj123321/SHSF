package org.zyj.shsf.webadmin.exception;

public class DalException extends RuntimeException {

	public DalException() {
		super();
	}

	public DalException(String message) {
		super(message);
	}

	public DalException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

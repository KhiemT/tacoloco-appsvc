package com.tacoloco.exception.checked;

public class BusinessCheckedException extends Exception {

	protected String code = "Unknown";

	public BusinessCheckedException() {
        super();
    }

	public BusinessCheckedException(Throwable cause) {
        super(cause);
    }

	public BusinessCheckedException(String message) {
        super(message);
    }

	public BusinessCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

	public String getCode() {
		return code;
	}
}

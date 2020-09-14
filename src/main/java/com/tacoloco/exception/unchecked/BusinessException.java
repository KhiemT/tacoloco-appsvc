package com.tacoloco.exception.unchecked;

public class BusinessException extends RuntimeException {

	protected String code = "Unknown";
	
	public BusinessException() {
        super();
    }
	
	public BusinessException(Throwable cause) {
        super(cause);
    }
	
	public BusinessException(String message) {
        super(message);
    }
	
	public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public String getCode() {
		return code;
	}
}

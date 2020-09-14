package com.tacoloco.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View Model for transferring error message with a list of field errors.
 */
public class ErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String code;
    private final String message;
    private final String extraMessage;

    private List<FieldErrorVM> fieldErrors;

    public ErrorVM(String code) {
        this(code, null);
    }
    
    public ErrorVM(String code, String message) {
        this(code, message, null);
    }
    
    public ErrorVM(String code, String message, String extraMessage) {
        this.code = code;
        this.message = message;
        this.extraMessage = extraMessage;
    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorVM(objectName, field, message));
    }
    
    public String getCode() {
		return code;
	}

    public String getMessage() {
        return message;
    }
    
    public String getExtraMessage() {
        return this.extraMessage;
    }

    public List<FieldErrorVM> getFieldErrors() {
        return fieldErrors;
    }
}

package com.tacoloco.exception.unchecked;

/**
 * An invalid order exception in general.
 */
public class InvalidOrderRequestException extends BusinessException {
    public InvalidOrderRequestException() {
        this(null);
    }

    public InvalidOrderRequestException(Throwable cause) {
        super("product.order.request.invalid", cause);
        this.code = "00003";
    }
}

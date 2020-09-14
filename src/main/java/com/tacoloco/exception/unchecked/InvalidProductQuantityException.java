package com.tacoloco.exception.unchecked;

/**
 * An invalid input of product quantity.
 */
public class InvalidProductQuantityException extends BusinessException {
    public InvalidProductQuantityException() {
        this(null);
    }

    public InvalidProductQuantityException(Throwable cause) {
        super("product.invalid.quantity", cause);
        this.code = "00002";
    }
}

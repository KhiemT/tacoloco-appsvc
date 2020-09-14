package com.tacoloco.exception.unchecked;

/**
 * A product is not found from the system
 */
public class ProductInstanceNotFoundException extends BusinessException {
    public ProductInstanceNotFoundException() {
        this(null);
    }
    public ProductInstanceNotFoundException(Throwable cause) {
        super("product.instance.not.found", cause);
        this.code = "00001";
    }

}

package com.tacoloco.service.product;

public class Veggie extends Product {

    public static final double DEFAULT_PRICE = 2.5;
    public static final String SHORT_NAME_EN = "Veggie Taco";

    /**
     * Create a new Veggie
     * @param id product identify.
     */
    public Veggie(String id) {
        super(id, SHORT_NAME_EN, DEFAULT_PRICE);
    }
}

package com.tacoloco.service.product;

public class Chorizo extends Product {

    public static final double DEFAULT_PRICE = 3.5;
    public static final String SHORT_NAME_EN = "Chorizo Taco";

    public Chorizo(String id) {
        super(id, SHORT_NAME_EN, DEFAULT_PRICE);
    }
}

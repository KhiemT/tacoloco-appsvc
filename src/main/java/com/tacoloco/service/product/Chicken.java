package com.tacoloco.service.product;

public class Chicken extends Product {

    public static final double DEFAULT_PRICE = 3.0;
    public static final String SHORT_NAME_EN = "Chicken Taco";

    public Chicken(String id) {
        super(id, SHORT_NAME_EN, DEFAULT_PRICE);
    }
}

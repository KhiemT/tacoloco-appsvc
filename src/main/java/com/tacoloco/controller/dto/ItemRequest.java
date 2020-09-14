package com.tacoloco.controller.dto;

public class ItemRequest {

    private String product;

    private int quantity;

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemRequest(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

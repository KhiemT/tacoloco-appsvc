package com.tacoloco.service.product;

/**
 * Add To cart Model
 */
public class AddToCart {
    private int quantity;
    private Product product;

    public AddToCart(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}

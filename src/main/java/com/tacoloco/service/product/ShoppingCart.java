package com.tacoloco.service.product;

import com.tacoloco.exception.unchecked.InvalidProductQuantityException;

import java.util.ArrayList;
import java.util.List;

/**
 * Illustrate a shopping cart example once a customer is able to add a product to cart
 */
public class ShoppingCart {

    private final List<AddToCart> addToCartList = new ArrayList<>();

    private final ProductFactory productFactory = new ConcreteProductFactory();

    /**
     * Pick up product and quantity to add to cart.
     * @param productInstance a selected product.
     * @param quantity number of product.
     * @throws InvalidProductQuantityException
     */
    public void addToCart(ProductInstance productInstance, int quantity) {
        if (quantity > 0) {
            Product product = productFactory.createProduct(productInstance);

            addToCartList.add(new AddToCart(product, quantity));
        } else {
            throw new InvalidProductQuantityException();
        }
    }

    /**
     * Count total number of products in a cart
     * @return total quantity.
     */
    public int countTotalQuantity() {
        return addToCartList.stream().mapToInt(AddToCart::getQuantity).sum();
    }

    /**
     * Total price of all products in a cart
     * @return total price
     */
    public float calculateTotalPrices() {
        float totalPrice = 0;
        for (AddToCart cart : addToCartList) {
            totalPrice += cart.getProduct().getBasePrice() * cart.getQuantity();
        }
        return totalPrice;
    }
}

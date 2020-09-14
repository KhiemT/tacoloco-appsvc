package com.tacoloco.service.product;

import com.tacoloco.exception.unchecked.InvalidProductQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {
    @Test
    void createShoppingCartSuccess() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(ProductInstance.BEEF, 1);
        shoppingCart.addToCart(ProductInstance.CHICKEN, 2);
        shoppingCart.addToCart(ProductInstance.CHORIZO, 3);
        shoppingCart.addToCart(ProductInstance.VEGGIE, 4);

        Assertions.assertEquals(10, shoppingCart.countTotalQuantity());
        Assertions.assertEquals(29.5, shoppingCart.calculateTotalPrices());

    }

    @Test
    void createShoppingCartQuantityNegative() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Exception exception = Assertions.assertThrows(InvalidProductQuantityException.class,
                () -> shoppingCart.addToCart(ProductInstance.BEEF, 0));

        String expectedMessage = "product.invalid.quantity";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void createShoppingCartEmpty() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Assertions.assertEquals(0, shoppingCart.countTotalQuantity());
        Assertions.assertEquals(0, shoppingCart.calculateTotalPrices());
    }
}

package com.tacoloco.service.product;

import com.tacoloco.service.discount.common.DiscountOrder;
import com.tacoloco.service.discount.common.DiscountRule;
import com.tacoloco.service.discount.common.InputForDiscountRules;
import com.tacoloco.service.discount.common.RuleChainFactory;
import com.tacoloco.service.discount.rule.RuleApplyDiscountHoliday;
import com.tacoloco.service.discount.rule.RuleApplyDiscountMore4ProductsInOneOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShoppingCartDiscountRulesTest {
    private DiscountRule discountRule = null;

    @BeforeEach
    public void initRuleConfiguration(){


        discountRule = RuleChainFactory.getInstance()
                .createDiscountRuleChainApplied(
                        new RuleApplyDiscountHoliday(),
                        new RuleApplyDiscountMore4ProductsInOneOrder());

    }

    @Test
    @DisplayName("Price Total with product number >= 4")
    void priceTotalWithMore4ProductsTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(ProductInstance.BEEF, 2);
        shoppingCart.addToCart(ProductInstance.CHICKEN, 1);
        shoppingCart.addToCart(ProductInstance.VEGGIE, 1);
        shoppingCart.addToCart(ProductInstance.CHORIZO, 1);

        float totalPrice = shoppingCart.calculateTotalPrices();
        int totalQuality = shoppingCart.countTotalQuantity();

        DiscountOrder order = new DiscountOrder(totalPrice);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, totalQuality, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(12, order.getPriceTotal());
    }

    @Test
    @DisplayName("Price Total with product number < 4")
    void priceTotalWithLess4ProductsTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(ProductInstance.CHICKEN, 1);
        shoppingCart.addToCart(ProductInstance.VEGGIE, 1);
        shoppingCart.addToCart(ProductInstance.CHORIZO, 1);

        float totalPrice = shoppingCart.calculateTotalPrices();
        int totalQuality = shoppingCart.countTotalQuantity();

        DiscountOrder order = new DiscountOrder(totalPrice);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, totalQuality, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(9, order.getPriceTotal());
    }

    @Test
    @DisplayName("Price Total with 1 product and Quality < 4")
    void priceTotalWithQualityLess4Test() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(ProductInstance.VEGGIE, 3);

        float totalPrice = shoppingCart.calculateTotalPrices();
        int totalQuality = shoppingCart.countTotalQuantity();

        DiscountOrder order = new DiscountOrder(totalPrice);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, totalQuality, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(7.5, order.getPriceTotal());
    }
    @Test
    @DisplayName("Price Total with 1 product and Quality > 4")
    void priceTotalWithQualityMore4Test() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(ProductInstance.VEGGIE, 99);

        float totalPrice = shoppingCart.calculateTotalPrices();
        int totalQuality = shoppingCart.countTotalQuantity();

        DiscountOrder order = new DiscountOrder(totalPrice);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, totalQuality, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(198, order.getPriceTotal());
    }
    @Test
    @DisplayName("Price Total with product number Integer.MAX_VALUE")
    void priceTotalWithMaxProductsTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(ProductInstance.CHICKEN, Integer.MAX_VALUE);

        float totalPrice = shoppingCart.calculateTotalPrices();
        int totalQuality = shoppingCart.countTotalQuantity();

        DiscountOrder order = new DiscountOrder(totalPrice);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, totalQuality, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertNotEquals(order.getPriceTotal(), 6442450941F, 0.0);

    }
}

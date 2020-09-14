package com.tacoloco.service;

import com.tacoloco.controller.dto.ItemRequest;
import com.tacoloco.controller.dto.OrderRequest;
import com.tacoloco.exception.unchecked.InvalidOrderRequestException;
import com.tacoloco.exception.unchecked.ProductInstanceNotFoundException;
import com.tacoloco.service.discount.common.DiscountOrder;
import com.tacoloco.service.discount.common.DiscountRule;
import com.tacoloco.service.discount.common.InputForDiscountRules;
import com.tacoloco.service.product.ProductInstance;
import com.tacoloco.service.product.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final DiscountRule discountRule;

    @Autowired
    public ShoppingCartServiceImpl(DiscountRule discountRule) {
        this.discountRule = discountRule;
    }

    /**
     * Process an onder to get total price including discount applied.
     * @implNote In real word, the price data type should be BigDecimal
     * @param orderRequest an order information
     * @return total price per order
     */
    public float calTotalPriceInOrder(OrderRequest orderRequest) {

        verifyOrderRequest(orderRequest);

        ShoppingCart shoppingCart = new ShoppingCart();

        for (ItemRequest item : orderRequest.getItems()) {
            shoppingCart.addToCart(findAvailableProductInstance(item.getProduct()), item.getQuantity());
        }

        float totalPrice = shoppingCart.calculateTotalPrices();
        int totalQuality = shoppingCart.countTotalQuantity();

        DiscountOrder order = new DiscountOrder(totalPrice);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, totalQuality, false);
        discountRule.applyChain(inputForDiscountRules);
        return order.getPriceTotal();
    }

    /**
     * Get available product from system. In real word, it should retrieve from database
     * @param productInst Product Identify
     * @return ProductInstance
     */
    private ProductInstance findAvailableProductInstance(String productInst) {
        try {
            return ProductInstance.valueOf(productInst);

        } catch (Exception e) {
            throw new ProductInstanceNotFoundException();
        }
    }

    /**
     * Pre-check out a order. Here simplest verify an valid order.
     * @param orderRequest order information
     */
    private void verifyOrderRequest(OrderRequest orderRequest) {
        if (CollectionUtils.isEmpty(orderRequest.getItems())) {
            throw new InvalidOrderRequestException();
        }
    }
}

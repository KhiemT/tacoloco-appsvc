package com.tacoloco.controller;

import com.tacoloco.controller.dto.OrderRequest;
import com.tacoloco.controller.dto.OrderResponse;
import com.tacoloco.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/shopping-carts")
public class ShoppingCartResourceImpl implements ShoppingCartResource {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartResourceImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/total-price")
    public OrderResponse calTotalPriceInOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTotalPrice(shoppingCartService.calTotalPriceInOrder(orderRequest));
        return orderResponse;
    }

    @GetMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, Detroit Lab";
    }

}

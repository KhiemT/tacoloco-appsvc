package com.tacoloco.controller;

import com.tacoloco.controller.dto.OrderRequest;
import com.tacoloco.controller.dto.OrderResponse;

public interface ShoppingCartResource {

    OrderResponse calTotalPriceInOrder(OrderRequest orderRequest);

    String greeting();

}

package com.tacoloco.service;

import com.tacoloco.controller.dto.OrderRequest;

public interface ShoppingCartService {

    float calTotalPriceInOrder(OrderRequest orderRequest);

}

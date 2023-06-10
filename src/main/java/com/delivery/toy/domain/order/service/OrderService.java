package com.delivery.toy.domain.order.service;

import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.response.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse saveOrder(CreateOrderRequest request);
}
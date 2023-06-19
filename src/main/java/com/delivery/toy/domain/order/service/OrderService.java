package com.delivery.toy.domain.order.service;

import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse saveOrder(CreateOrderRequest request);
}
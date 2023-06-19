package com.delivery.toy.domain.order.controller;

import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.response.OrderResponse;
import com.delivery.toy.domain.order.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @PostMapping()
    public ResponseEntity<OrderResponse> saveOrder(
            @RequestBody CreateOrderRequest request) {
        OrderResponse response = orderServiceImpl.saveOrder(request);
        return  ResponseEntity.ok().body(response);
    }
}

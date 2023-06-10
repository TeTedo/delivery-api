package com.delivery.toy.domain.order.mapper;

import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.request.OrderRequest;
import com.delivery.toy.domain.order.dto.response.CreateOrderResponse;
import com.delivery.toy.domain.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public CreateOrderResponse toCreateOrderResponse(Order order){
        return CreateOrderResponse.builder()
                .id(order.getId())
                .food(order.getFood())
                .userId(order.getUserId())
                .count(order.getCount())
                .build();
    }

    // TODO userId 바꿀것
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .food(orderRequest.food())
                .userId(orderRequest.userId())
                .count(orderRequest.count())
                .build();
    }
}

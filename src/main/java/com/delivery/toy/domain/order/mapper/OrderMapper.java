package com.delivery.toy.domain.order.mapper;

import com.delivery.toy.domain.order.dto.request.OrderRequest;
import com.delivery.toy.domain.order.dto.response.OrderResponse;
import com.delivery.toy.domain.order.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    // TODO userId 바꿀것
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .food(orderRequest.food())
                .userId(orderRequest.userId())
                .count(orderRequest.count())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .food(order.getFood())
                .userId(order.getUserId())
                .count(order.getCount())
                .build();
    }
}

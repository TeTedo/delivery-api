package com.delivery.toy.domain.order.dto.response;

import com.delivery.toy.domain.food.model.Food;
import lombok.Builder;

@Builder
public record CreateOrderResponse(Long id, Food food, int userId, int count) {
    //TODO userId 타입 User로 바꿀것
}

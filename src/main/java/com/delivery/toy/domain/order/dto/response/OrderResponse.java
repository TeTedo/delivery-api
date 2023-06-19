package com.delivery.toy.domain.order.dto.response;

import com.delivery.toy.domain.food.model.Food;
import lombok.Builder;

@Builder
public record OrderResponse (
        Long id, Food food
        ,int userId, int count){
}

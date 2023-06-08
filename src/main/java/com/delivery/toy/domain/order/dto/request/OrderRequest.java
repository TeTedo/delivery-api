package com.delivery.toy.domain.order.dto.request;

import com.delivery.toy.domain.food.model.Food;
import lombok.Builder;

@Builder
public record OrderRequest(Food food, int userId, int count) {
    // TODO userId 교체
}

package com.delivery.toy.domain.food.dto.request;

import lombok.Builder;

@Builder
public record FindByFoodIdRequest(
        Long id) {
}

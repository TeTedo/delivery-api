package com.delivery.toy.domain.food.dto.response;

import lombok.Builder;

@Builder
public record CreateFoodResponse (
        Boolean status
){
}

package com.delivery.toy.domain.food.dto.response;

import lombok.Builder;

@Builder
public record FoodResponse(
        Long id,
        String name,
        double caloriePerGram,
        double carbohydratePerGram,
        double proteinPerGram,
        double provincePerGram,
        int grams,
        int price,
        String imgPath) {
}
package com.delivery.toy.domain.food.dto.request;

import lombok.Builder;

@Builder
public  record CreateFoodRequest(
        String name,
        double caloriePerGram,
        double carbohydratePerGram,
        double proteinPerGram,
        double provincePerGram,
        int grams,
        int price,
        String imgPath) {
}

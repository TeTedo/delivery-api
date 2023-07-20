package com.delivery.toy.domain.food.mapper;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.model.Food;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {

    public Food toFood(CreateFoodRequest createFoodRequest) {
        return Food.builder()
                .name(createFoodRequest.name())
                .price(createFoodRequest.price())
                .imgPath(createFoodRequest.imgPath())
                .build();
    }

    public FoodResponse toFoodResponse(Food food){
        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .price(food.getPrice())
                .imgPath(food.getImgPath())
                .build();
    }
}

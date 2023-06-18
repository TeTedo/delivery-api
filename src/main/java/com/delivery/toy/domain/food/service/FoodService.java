package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.FoodResponse;

public interface FoodService {

    FoodResponse saveFood(CreateFoodRequest foodDto);

    FoodResponse findByFoodId(FindByFoodIdRequest findByFoodIdRequest);

}

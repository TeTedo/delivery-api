package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.CreateFoodResponse;
import com.delivery.toy.domain.food.dto.response.FoodResponse;

public interface FoodService {

    CreateFoodResponse saveFood(CreateFoodRequest foodDto);

    FoodResponse findByFoodId(FindByFoodIdRequest findByFoodIdRequest);

}

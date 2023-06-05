package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.model.Food;

public interface FoodService {

    Food save(CreateFoodRequest foodDto);

    Food findById(FindByFoodIdRequest findByFoodIdRequest);

}

package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.exception.FoodIllegalException;
import com.delivery.toy.domain.food.mapper.FoodMapper;
import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

public interface FoodService {

    Food save(CreateFoodRequest foodDto);

    Food findById(FindByFoodIdRequest findByFoodIdRequest);

}

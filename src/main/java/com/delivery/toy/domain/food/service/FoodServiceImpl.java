package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.CreateFoodResponse;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.exception.FoodNotFoundException;
import com.delivery.toy.domain.food.mapper.FoodMapper;
import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService{

    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;

    @Override
    public CreateFoodResponse saveFood(CreateFoodRequest foodDto) {
        Food food = foodMapper.toFood(foodDto);
        foodRepository.save(food);

        return CreateFoodResponse
                .builder()
                .status(true)
                .build();
    }

    @Override
    public FoodResponse findByFoodId(FindByFoodIdRequest findByFoodIdRequest) {
        Food food = foodRepository.findById(findByFoodIdRequest.id())
                .orElseThrow(()-> new FoodNotFoundException("해당 id를 가진 Food가 없습니다."));
        
        return foodMapper.toFoodResponse(food);
    }
}

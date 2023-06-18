package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
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
    public FoodResponse saveFood(CreateFoodRequest foodDto) {
        Food food = foodMapper.toFood(foodDto);

        Food savedFood = foodRepository.save(food);

        return foodMapper.toFoodResponse(savedFood);
    }

    @Override
    public FoodResponse findByFoodId(FindByFoodIdRequest findByFoodIdRequest) {
        Food food = foodRepository.findById(findByFoodIdRequest.id())
                .orElseThrow(()-> new FoodNotFoundException("해당 id를 가진 Food가 없습니다."));
        
        return foodMapper.toFoodResponse(food);
    }
}

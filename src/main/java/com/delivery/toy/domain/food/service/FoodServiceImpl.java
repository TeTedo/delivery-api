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

@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService{

    private final FoodMapper foodMapper;
    private final FoodRepository foodRepository;

    @Override
    public Food save(CreateFoodRequest foodDto) {
        Food food = foodMapper.toFood(foodDto);
        Assert.notNull(food, "food는 null값이 될수 없습니다.");

        return foodRepository.save(food);
    }

    @Override
    public Food findById(FindByFoodIdRequest findByFoodIdRequest) {
        Food food =  foodRepository.findById(findByFoodIdRequest.id())
                .orElseThrow(()-> new FoodIllegalException("해당 id를 가진 Food가 없습니다."));

        return food;
    }
}

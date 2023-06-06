package com.delivery.toy.domain.food.controller;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.CreateFoodResponse;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.service.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodServiceImpl foodServiceImpl;

    @PostMapping("/foods")
    public ResponseEntity<CreateFoodResponse>  createFood(
            @RequestBody CreateFoodRequest request
            ){
        CreateFoodResponse response = foodServiceImpl.save(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/foods/{food-id}")
    public ResponseEntity<FoodResponse>  findByFoodId(
            @PathVariable("food-id") FindByFoodIdRequest request
            ){
        FoodResponse foodResponse = foodServiceImpl.findById(request);

        return ResponseEntity.ok().body(foodResponse);
    }

}

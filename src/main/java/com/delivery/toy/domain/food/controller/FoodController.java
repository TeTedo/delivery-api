package com.delivery.toy.domain.food.controller;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.CreateFoodResponse;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.service.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/foods")
@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodServiceImpl foodServiceImpl;

    @PostMapping()
    public ResponseEntity<CreateFoodResponse>  createFood(
            @RequestBody CreateFoodRequest request) {

        CreateFoodResponse response = foodServiceImpl.saveFood(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{food-id}")
    public ResponseEntity<FoodResponse>  findByFoodId(
            @PathVariable("food-id") Long id) {

        FindByFoodIdRequest request = FindByFoodIdRequest
                .builder()
                .id(id)
                .build();

        FoodResponse foodResponse = foodServiceImpl.findByFoodId(request);

        return ResponseEntity.ok().body(foodResponse);
    }
}

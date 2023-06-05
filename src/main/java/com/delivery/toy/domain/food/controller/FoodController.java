package com.delivery.toy.domain.food.controller;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.CreateFoodResponse;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.service.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodServiceImpl foodServiceImpl;

    @RequestMapping(value = "/foods")
    public ResponseEntity<CreateFoodResponse>  createFood(
            @RequestBody CreateFoodRequest request
            ){
        CreateFoodResponse response = foodServiceImpl.save(request);
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/foods/{food-id}")
    public ResponseEntity<FoodResponse>  findByFoodId(
            @PathVariable("food-id") FindByFoodIdRequest request
            ){
        FoodResponse foodResponse = foodServiceImpl.findById(request);

        return ResponseEntity.ok().body(foodResponse);
    }

}

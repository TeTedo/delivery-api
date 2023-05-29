package com.delivery.toy.domain.food.controller;

import com.delivery.toy.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;
}

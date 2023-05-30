package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
}

package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.repository.FoodRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FoodServiceTest {
    @Autowired
    FoodRepository foodRepository;

    @DisplayName("id를 받으면 조회한다")
    @Test
    void findById(){

    }
}

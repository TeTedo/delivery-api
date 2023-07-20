package com.delivery.toy.global.common.domain;


import com.delivery.toy.base.RepositoryTest;
import com.delivery.toy.domain.food.model.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BaseTimeEntityTest extends RepositoryTest {

    Food food;

    @BeforeEach
    void setUp(){
        String name = "salad2";
        int price = 12000;
        String imgPath = "tempImgPath";

        food = Food
                .builder()
                .name(name)
                .price(price)
                .imgPath(imgPath)
                .build();
    }

    @DisplayName("BaseTimeEntity column 확인")
    @Test
    void checkBaseTimeEntity(){
        // given
        foodRepository.save(food);

        // when
        List<Food> foodList = foodRepository.findAll();
        Food foundFood = foodList.get(0);

        // then
        Assertions.assertThat(foundFood)
                .hasFieldOrProperty("CreatedAt")
                .hasFieldOrProperty("UpdatedAt");
    }
}

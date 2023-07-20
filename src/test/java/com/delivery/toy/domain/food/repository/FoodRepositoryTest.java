package com.delivery.toy.domain.food.repository;

import com.delivery.toy.base.RepositoryTest;
import com.delivery.toy.domain.food.exception.FoodNotFoundException;
import com.delivery.toy.domain.food.model.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FoodRepositoryTest extends RepositoryTest {

    Food food;

    @BeforeEach
    void setUp(){
        String name = "salad";
        int price = 12000;
        String imgPath = "tempImgPath";

        food = Food
                .builder()
                .name(name)
                .price(price)
                .imgPath(imgPath)
                .build();
    }

    @DisplayName("Food 저장 테스트")
    @Test
    void save(){
        // when
        Food savedFood = foodRepository.save(food);

        // then
        Assertions.assertThat(savedFood).isEqualTo(food);
    }

    @DisplayName("Food 조회 테스트")
    @Test
    void findById(){
        // given
        Food savedFood = foodRepository.save(food);
        Long id = savedFood.getId();

        // when
        Food foundFood = foodRepository.findById(id)
                .orElseThrow(()-> new FoodNotFoundException("해당 Id는 없는 Food입니다."));

        // then
        Assertions.assertThat(foundFood).isEqualTo(food);
    }
}

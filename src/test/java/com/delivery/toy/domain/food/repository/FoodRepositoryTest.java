package com.delivery.toy.domain.food.repository;

import com.delivery.toy.domain.food.exception.FoodIllegalArgumentException;
import com.delivery.toy.domain.food.model.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;

    Food food;

    @BeforeEach
    void setUp(){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";

        food = Food
                .builder()
                .name(name)
                .caloriePerGram(caloriePerGram)
                .carbohydratePerGram(carbohydratePerGram)
                .proteinPerGram(proteinPerGram)
                .provincePerGram(provincePerGram)
                .grams(grams)
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
    void find(){
        // given
        save();

        // when
        Food foundFood = foodRepository.findById(1L)
                .orElseThrow(()-> new FoodIllegalArgumentException("해당 Id는 없는 Food입니다."));

        // then
        Assertions.assertThat(foundFood).isEqualTo(food);

    }
}

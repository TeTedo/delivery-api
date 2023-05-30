package com.delivery.toy.domain.food.repository;

import static org.assertj.core.api.Assertions.*;
import com.delivery.toy.domain.food.model.Food;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@DataJpaTest
public class FoodRepositoryTest {
    @Autowired
    private FoodRepository foodRepository;

    private Food food;

    @BeforeEach
    void createFood(){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "temp";

        Food food = Food.builder()
                .name(name)
                .caloriePerGram(caloriePerGram)
                .carbohydratePerGram(carbohydratePerGram)
                .proteinPerGram(proteinPerGram)
                .provincePerGram(provincePerGram)
                .grams(grams)
                .price(price)
                .imgPath(imgPath)
                .build();

        this.food = food;
    }
    @DisplayName("food 저장 테스트")
    @Test
    void saveFood(){

        if(food == null){
            throw new InvalidDataAccessApiUsageException("Food 객체 생성 실패로 save 불가");
        }

        Food savedFood = foodRepository.save(food);

        assertThat(savedFood).isSameAs(food);
        assertThat(savedFood)
                .hasFieldOrPropertyWithValue("name","salad")
                .hasFieldOrPropertyWithValue("caloriePerGram",1.2)
                .hasFieldOrPropertyWithValue("carbohydratePerGram",0.03)
                .hasFieldOrPropertyWithValue("proteinPerGram",0.05)
                .hasFieldOrPropertyWithValue("provincePerGram",0.01)
                .hasFieldOrPropertyWithValue("grams",250)
                .hasFieldOrPropertyWithValue("price",12000)
                .hasFieldOrPropertyWithValue("imgPath","temp");
    }

    @DisplayName("food 조회 테스트")
    @Test
    void searchFood(){

        if(food == null){
            throw new InvalidDataAccessApiUsageException("Food 객체 생성 실패로 save 불가");
        }

        Food savedFood = foodRepository.save(food);

        Long id = savedFood.getId();

        Food searchedFood = foodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id는 없습니다"));

        Assertions.assertThat(searchedFood).isSameAs(savedFood);
        Assertions.assertThat(searchedFood).isSameAs(food);
    }
}

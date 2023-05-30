package com.delivery.toy.domain.food.repository;

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

    @DisplayName("food 저장 테스트")
    @Test
    void saveFood(){

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


        if(food == null){
            throw new InvalidDataAccessApiUsageException("Food 객체 생성 실패로 save 불가");
        }

        Food savedFood = foodRepository.save(food);

        Assertions.assertThat(savedFood).isSameAs(food);
        Assertions.assertThat(savedFood.getName()).isEqualTo(name);
        Assertions.assertThat(savedFood.getCaloriePerGram()).isEqualTo(caloriePerGram);
        Assertions.assertThat(savedFood.getCarbohydratePerGram()).isEqualTo(carbohydratePerGram);
        Assertions.assertThat(savedFood.getProteinPerGram()).isEqualTo(proteinPerGram);
        Assertions.assertThat(savedFood.getProvincePerGram()).isEqualTo(provincePerGram);
        Assertions.assertThat(savedFood.getGrams()).isEqualTo(grams);
        Assertions.assertThat(savedFood.getPrice()).isEqualTo(price);
        Assertions.assertThat(savedFood.getImgPath()).isEqualTo(imgPath);

    }

    @DisplayName("food 조회 테스트")
    @Test
    void searchFood(){
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

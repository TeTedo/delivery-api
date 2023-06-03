package com.delivery.toy.domain.food.service;


import net.minidev.json.JSONUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class FoodServiceTest {
    private FoodService foodService;
    private Long foodId;
    private FoodMapper foodMapper;
    private FoodRepositoryImpl foodRepositoryImpl;

    @BeforeEach
    void setUp(){
        foodService= new FoodService();
        foodId = 0L;
        foodMapper = new FoodMapper();
        foodRepositoryImpl =  new FoodRepositoryImpl();
    }


    @DisplayName("Food dto를 받아 food Entity로 바꾼 후 저장한다.")
    @Test
    void saveFood(){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";

        CreateRequest foodDto = new CreateRequest(
                name,
                caloriePerGram,
                carbohydratePerGram,
                proteinPerGram,
                provincePerGram,
                grams,
                price,
                imgPath
        );
        Assertions.assertThat(foodDto).isNotNull();

        Food food = foodService.save(foodDto);

        Assertions.assertThat(food)
                .hasFieldOrPropertyWithValue("name","salad")
                .hasFieldOrPropertyWithValue("caloriePerGram",1.2)
                .hasFieldOrPropertyWithValue("carbohydratePerGram",0.03)
                .hasFieldOrPropertyWithValue("proteinPerGram",0.05)
                .hasFieldOrPropertyWithValue("provincePerGram",0.01)
                .hasFieldOrPropertyWithValue("grams",250)
                .hasFieldOrPropertyWithValue("price",12000)
                .hasFieldOrPropertyWithValue("imgPath","tempImgPath");
    }

    @DisplayName("id를 받아 조회한다.")
    @Test
    void findById() {
        saveFood();
        FindByIdRequest findByIdRequest = new FindByIdRequest(1L);
        Food food = foodService.findById(findByIdRequest);
        Assertions.assertThat(food).hasFieldOrPropertyWithValue("id",1L);
    }




    private class FoodService {

        public Food save(CreateRequest foodDto) {
            Food food = foodMapper.toFood(foodDto);
            Assert.notNull(food, "food는 null값이 될수 없습니다.");
            return foodRepositoryImpl.save(food);
        }

        public Food findById(FindByIdRequest findByIdRequest) {
            Food food =  foodRepositoryImpl.findById(findByIdRequest.id);
            return food;
        }
    }

    private class Food {
        private  final Long id;
        private  final String name;
        private  final double caloriePerGram;
        private  final double carbohydratePerGram;
        private  final double proteinPerGram;
        private  final double provincePerGram;
        private  final int grams;
        private  final int price;
        private  final String imgPath;


        public Food(String name, double caloriePerGram, double carbohydratePerGram, double proteinPerGram, double provincePerGram, int grams, int price, String imgPath) {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.isTrue(caloriePerGram > 0, "그램당 칼로리는 0보다 큽니다.");
            Assert.isTrue(carbohydratePerGram > 0, "그램당 탄수화물은 0보다 큽니다");
            Assert.isTrue(proteinPerGram > 0, "그램당 단백질은 0보다 큽니다");
            Assert.isTrue(provincePerGram > 0, "그램당 지방은 0보다 큽니다");
            Assert.isTrue(price > 0, "가격은 0 보다 커야 합니다.");
            Assert.notNull(imgPath, "사진 url은 필수입니다.");
            this.id = ++foodId;
            this.name = name;
            this.caloriePerGram = caloriePerGram;
            this.carbohydratePerGram = carbohydratePerGram;
            this.proteinPerGram = proteinPerGram;
            this.provincePerGram = provincePerGram;
            this.grams = grams;
            this.price = price;
            this.imgPath = imgPath;
        }
    }

    private record CreateRequest(String name, double caloriePerGram, double carbohydratePerGram, double proteinPerGram, double provincePerGram, int grams, int price, String imgPath) {

    }

    private class FoodMapper {
        public Food toFood(CreateRequest foodDto) {
            return new Food(foodDto.name, foodDto.caloriePerGram, foodDto.carbohydratePerGram, foodDto.proteinPerGram, foodDto.provincePerGram, foodDto.grams, foodDto.price, foodDto.imgPath);
        }
    }

    private interface FoodRepository {
        Food save(Food food);
        Food findById(Long id);
    }

    private class FoodRepositoryImpl implements FoodRepository{
        private Food food;
        @Override
        public Food save(Food food) {
            this.food = food;
            return food;
        }

        @Override
        public Food findById(Long id) {
            if(this.food.id == id) return this.food;
            else throw new FoodIllegalException("존재하지 않는 id입니다.");
        }
    }

    private class FoodIllegalException extends IllegalArgumentException {
        public FoodIllegalException(String s) {
            super(s);
        }
    }

    private record FindByIdRequest(Long id) {
    }
}

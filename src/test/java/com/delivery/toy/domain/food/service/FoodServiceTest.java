package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.mapper.FoodMapper;
import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
    @Mock
    private FoodMapper foodMapper;

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodServiceImpl foodServiceImpl;

    Food mockedFood;

    @BeforeEach
    void setUp(){
        Long foodId = 1L;
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";

        this.mockedFood = Food.builder()
                .id(foodId)
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

    @DisplayName("FoodRequestDto를 받아Entity로 바꾸고 저장")
    @Test
    void saveFood(){
        // given
        CreateFoodRequest foodDto = createFoodRequest();

        Mockito.when(foodMapper.toFood(Mockito.any(CreateFoodRequest.class)))
                .thenReturn(mockedFood);

        Mockito.when(foodRepository.save(Mockito.any(Food.class)))
                .thenReturn(mockedFood);

        // when
        Food food = foodServiceImpl.save(foodDto);
        System.out.println(food);

        // then
        Assertions.assertThat(food)
                .hasFieldOrPropertyWithValue("name","salad")
                .hasFieldOrPropertyWithValue("caloriePerGram",1.2)
                .hasFieldOrPropertyWithValue("carbohydratePerGram",0.03)
                .hasFieldOrPropertyWithValue("proteinPerGram",0.05)
                .hasFieldOrPropertyWithValue("provincePerGram",0.01)
                .hasFieldOrPropertyWithValue("grams",250)
                .hasFieldOrPropertyWithValue("price",12000)
                .hasFieldOrPropertyWithValue("imgPath","tempImgPath");

        Mockito.verify(foodMapper).toFood(foodDto);
        Mockito.verify(foodRepository).save(food);
    }

    private CreateFoodRequest createFoodRequest(){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";


        return CreateFoodRequest
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
    @DisplayName("foodId를 받아 조회")
    @Test
    void findById() {
        // given
        saveFood();
        Long foodId = 1L;

        FindByFoodIdRequest findByFoodIdRequest = FindByFoodIdRequest
                .builder()
                .id(foodId)
                .build();

        Mockito.when(foodRepository.findById(foodId))
                .thenReturn(Optional.of(mockedFood));

        // when
        Food food = foodServiceImpl.findById(findByFoodIdRequest);

        // then
        Assertions.assertThat(food)
                .hasFieldOrPropertyWithValue("id",foodId);

        Mockito.verify(foodRepository).findById(foodId);
    }
}

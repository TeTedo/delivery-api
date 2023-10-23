package com.delivery.toy.domain.food.service;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
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
        void setUp() {
                Long foodId = 1L;
                String name = "salad";
                int price = 12000;
                String imgPath = "tempImgPath";

                this.mockedFood = Food.builder()
                                .id(foodId)
                                .name(name)
                                .price(price)
                                .imgPath(imgPath)
                                .build();
        }

        @DisplayName("FoodRequestDto를 받아Entity로 바꾸고 저장")
        @Test
        void saveFood() {
                // given
                CreateFoodRequest foodDto = getCreateFoodRequest();

                Mockito.when(foodMapper.toFood(foodDto))
                                .thenReturn(mockedFood);

                Mockito.when(foodRepository.save(Mockito.any(Food.class)))
                                .thenReturn(mockedFood);

                FoodResponse mockFoodResponse = getFoodResponse();
                Mockito.when(foodMapper.toFoodResponse(mockedFood))
                                .thenReturn(mockFoodResponse);
                // when
                FoodResponse foodResponse = foodServiceImpl.saveFood(foodDto);

                // then
                Assertions.assertThat(foodResponse)
                                .isEqualTo(mockFoodResponse);

                Mockito.verify(foodMapper).toFood(foodDto);
                Mockito.verify(foodRepository).save(mockedFood);
                Mockito.verify(foodMapper).toFoodResponse(mockedFood);
        }

        private CreateFoodRequest getCreateFoodRequest() {
                String name = "salad";
                int price = 12000;
                String imgPath = "tempImgPath";

                return CreateFoodRequest
                                .builder()
                                .name(name)
                                .price(price)
                                .imgPath(imgPath)
                                .build();
        }

        @DisplayName("foodId를 받아 조회")
        @Test
        void findByFoodId() {
                // given
                saveFood();
                Long foodId = 1L;

                FindByFoodIdRequest findByFoodIdRequest = FindByFoodIdRequest
                                .builder()
                                .id(foodId)
                                .build();
                FoodResponse response = getFoodResponse();

                Mockito.when(foodMapper.toFoodResponse(Mockito.any(Food.class)))
                                .thenReturn(response);

                Mockito.when(foodRepository.findById(foodId))
                                .thenReturn(Optional.of(mockedFood));

                // when
                FoodResponse foodResponse = foodServiceImpl.findByFoodId(findByFoodIdRequest);

                // then
                Assertions.assertThat(foodResponse)
                                .hasFieldOrPropertyWithValue("id", foodId);

                Mockito.verify(foodRepository).findById(foodId);
        }

        private FoodResponse getFoodResponse() {
                Long id = 1L;
                String name = "salad";
                int price = 12000;
                String imgPath = "tempImgPath";

                return FoodResponse
                                .builder()
                                .id(id)
                                .name(name)
                                .price(price)
                                .imgPath(imgPath)
                                .build();
        }
}

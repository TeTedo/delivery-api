package com.delivery.toy.domain.order.service;

import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.request.OrderRequest;
import com.delivery.toy.domain.order.dto.response.CreateOrderResponse;
import com.delivery.toy.domain.order.mapper.OrderMapper;
import com.delivery.toy.domain.order.model.Order;
import com.delivery.toy.domain.order.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class OrderServiceTest {

    @Autowired
    FoodRepository foodRepository;

    @Mock
    FoodRepository mockedFoodRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderMapper orderMapper;

    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    Order mockedOrder;

    Food food;

    @BeforeEach
    void setUp(){
        Long id = 1L;
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";

        food = Food.builder()
                .id(id)
                .name(name)
                .caloriePerGram(caloriePerGram)
                .carbohydratePerGram(carbohydratePerGram)
                .proteinPerGram(proteinPerGram)
                .provincePerGram(provincePerGram)
                .grams(grams)
                .price(price)
                .imgPath(imgPath)
                .build();

        Food savedFood = foodRepository.save(food);

        int userId = 1;
        int count = 1;

        mockedOrder = Order.builder()
                .food(savedFood)
                .userId(userId)
                .count(count)
                .build();

    }

    @DisplayName("foodId와 count를 받아 주문을 저장한다.")
    @Test
    void save() throws Exception{
        // given
        Long foodId = 1L;
        int userId = 1;
        int count = 1;

        CreateOrderRequest request = getCreateOrderRequest(foodId, count);

        OrderRequest orderRequest = getOrderRequest(userId, count);

        Long orderId = 1L;
        CreateOrderResponse mockResponse = getOrderResponse(userId, count, orderId);

        Mockito.when(mockedFoodRepository.findById(foodId))
                .thenReturn(Optional.of(food));

        Mockito.when(orderMapper.toOrder(orderRequest))
                .thenReturn(mockedOrder);

        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(mockedOrder);

        Mockito.when(orderMapper.toCreateOrderResponse(Mockito.any(Order.class)))
                .thenReturn(mockResponse);

        // when
        CreateOrderResponse response = orderServiceImpl.saveOrder(request);

        // then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(mockResponse);
        Assertions.assertThat(response.food()).isEqualTo(food);
        Assertions.assertThat(response.userId()).isEqualTo(userId);
        Assertions.assertThat(response.count()).isEqualTo(count);

        Mockito.verify(mockedFoodRepository).findById(foodId);
        Mockito.verify(orderMapper).toOrder(orderRequest);
        Mockito.verify(orderRepository).save(mockedOrder);
        Mockito.verify(orderMapper).toCreateOrderResponse(mockedOrder);
    }

    private CreateOrderResponse getOrderResponse(int userId, int count, Long orderId) {
        return CreateOrderResponse.builder()
                .id(orderId)
                .food(food)
                .userId(userId)
                .count(count)
                .build();
    }

    private static CreateOrderRequest getCreateOrderRequest(Long foodId, int count) {
        return CreateOrderRequest.builder()
                .foodId(foodId)
                .count(count)
                .build();
    }

    private OrderRequest getOrderRequest(int userId, int count) {
        return OrderRequest.builder()
                .food(food)
                .userId(userId)
                .count(count)
                .build();
    }
}

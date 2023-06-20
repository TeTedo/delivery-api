package com.delivery.toy.domain.order.service;

import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.request.OrderRequest;
import com.delivery.toy.domain.order.dto.response.CreateOrderResponse;
import com.delivery.toy.domain.order.dto.response.OrderResponse;
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
    void saveOrder() {
        // given
        Long foodId = 1L;
        int userId = 1;
        int count = 1;

        CreateOrderRequest request = getCreateOrderRequest(foodId, count);

        OrderRequest orderRequest = getOrderRequest(userId, count);

        Mockito.when(mockedFoodRepository.findById(foodId))
                .thenReturn(Optional.of(food));

        Mockito.when(orderMapper.toOrder(orderRequest))
                .thenReturn(mockedOrder);

        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(mockedOrder);

        OrderResponse orderResponse = getOrderResponse();

        Mockito.when(orderMapper.toOrderResponse(mockedOrder))
                .thenReturn(orderResponse);

        // when
        OrderResponse response = orderServiceImpl.saveOrder(request);

        // then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(orderResponse);
        Assertions.assertThat(response)
                .hasFieldOrPropertyWithValue("id", mockedOrder.getId())
                .hasFieldOrPropertyWithValue("food", mockedOrder.getFood())
                .hasFieldOrPropertyWithValue("userId", mockedOrder.getUserId())
                .hasFieldOrPropertyWithValue("count", mockedOrder.getCount());

        Mockito.verify(mockedFoodRepository).findById(foodId);
        Mockito.verify(orderMapper).toOrder(orderRequest);
        Mockito.verify(orderRepository).save(mockedOrder);
        Mockito.verify(orderMapper).toOrderResponse(mockedOrder);
    }

    private OrderResponse getOrderResponse() {
        return OrderResponse.builder()
                .id(mockedOrder.getId())
                .food(mockedOrder.getFood())
                .userId(mockedOrder.getUserId())
                .count(mockedOrder.getCount())
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

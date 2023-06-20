package com.delivery.toy.domain.order.repository;

import com.delivery.toy.base.RepositoryTest;
import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.order.model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderRepositoryTest extends RepositoryTest {

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

        food = Food.builder()
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

    @DisplayName("Order 저장 테스트")
    @Test
    void save(){
        // given
        // TODO user 도메인 추가 후 userId 수정
        int userId = 1;
        int count = 1;

        Food savedFood = foodRepository.save(food);

        Order order = Order.builder()
                .food(savedFood)
                .userId(userId)
                .count(count)
                .build();

        // when
        Order savedOrder = orderRepository.save(order);

        //then
        Assertions.assertThat(savedOrder).isNotNull();
        Assertions.assertThat(savedOrder.getId()).isNotNull();
        Assertions.assertThat(savedOrder.getFood()).isEqualTo(savedFood);
        Assertions.assertThat(savedOrder.getUserId()).isEqualTo(userId);
        Assertions.assertThat(savedOrder.getCount()).isEqualTo(count);
        Assertions.assertThat(savedOrder).isEqualTo(order);
    }
}

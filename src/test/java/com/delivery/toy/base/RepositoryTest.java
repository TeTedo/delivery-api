package com.delivery.toy.base;

import com.delivery.toy.domain.food.repository.FoodRepository;
import com.delivery.toy.domain.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    public FoodRepository foodRepository;

    @Autowired
    public OrderRepository orderRepository;
}

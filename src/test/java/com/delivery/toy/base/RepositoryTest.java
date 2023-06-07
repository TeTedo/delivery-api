package com.delivery.toy.base;

import com.delivery.toy.domain.food.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    public FoodRepository foodRepository;
}

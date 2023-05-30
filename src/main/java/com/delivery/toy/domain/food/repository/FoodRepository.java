package com.delivery.toy.domain.food.repository;

import com.delivery.toy.domain.food.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
}

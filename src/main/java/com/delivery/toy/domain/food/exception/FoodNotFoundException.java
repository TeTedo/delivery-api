package com.delivery.toy.domain.food.exception;

public class FoodNotFoundException extends IllegalArgumentException {
    public FoodNotFoundException(String s) {
        super(s);
    }
}

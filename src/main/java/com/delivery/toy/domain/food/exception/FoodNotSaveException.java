package com.delivery.toy.domain.food.exception;

public class FoodNotSaveException extends RuntimeException{
    public FoodNotSaveException(String msg) {
        super(msg);
    }
}

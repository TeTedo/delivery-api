package com.delivery.toy.domain.food.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private double caloriePerGram;
    @Column(nullable = false)
    private double carbohydratePerGram;
    @Column(nullable = false)
    private double proteinPerGram;
    @Column(nullable = false)
    private double provincePerGram;
    @Column(nullable = false)
    private int grams;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 255)
    private String imgPath;

    @Builder
    public Food(Long id, String name, double caloriePerGram, double carbohydratePerGram, double proteinPerGram, double provincePerGram, int grams, int price, String imgPath) {
        this.id = id;
        this.name = name;
        this.caloriePerGram = caloriePerGram;
        this.carbohydratePerGram = carbohydratePerGram;
        this.proteinPerGram = proteinPerGram;
        this.provincePerGram = provincePerGram;
        this.grams = grams;
        this.price = price;
        this.imgPath = imgPath;
    }
}

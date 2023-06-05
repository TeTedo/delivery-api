package com.delivery.toy.domain.food.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  final Long id;

    @Column
    private  final String name;

    @Column
    private  final double caloriePerGram;

    @Column
    private  final double carbohydratePerGram;

    @Column
    private  final double proteinPerGram;

    @Column
    private  final double provincePerGram;

    @Column
    private  final int grams;

    @Column
    private  final int price;

    @Column
    private  final String imgPath;

    @Builder
    public Food(
            Long id,
            String name,
            double caloriePerGram,
            double carbohydratePerGram,
            double proteinPerGram,
            double provincePerGram,
            int grams,
            int price,
            String imgPath
    ) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(caloriePerGram > 0, "그램당 칼로리는 0보다 큽니다.");
        Assert.isTrue(carbohydratePerGram > 0, "그램당 탄수화물은 0보다 큽니다");
        Assert.isTrue(proteinPerGram > 0, "그램당 단백질은 0보다 큽니다");
        Assert.isTrue(provincePerGram > 0, "그램당 지방은 0보다 큽니다");
        Assert.isTrue(price > 0, "가격은 0 보다 커야 합니다.");
        Assert.notNull(imgPath, "사진 url은 필수입니다.");

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

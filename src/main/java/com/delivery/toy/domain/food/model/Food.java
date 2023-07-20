package com.delivery.toy.domain.food.model;

import com.delivery.toy.global.common.domain.BaseTImeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;


@Getter
@Table(name = "foods")
@NoArgsConstructor
@Entity
public class Food extends BaseTImeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;

    @Column(nullable = false, length = 100)
    private  String name;

    @Column(nullable = false)
    private  int price;

    @Column(nullable = false, length = 255)
    private  String imgPath;

    @Builder
    public Food(
            Long id,
            String name,
            int price,
            String imgPath
    ) {
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.isTrue(price > 0, "가격은 0 보다 커야 합니다.");
        Assert.notNull(imgPath, "사진 url은 필수입니다.");

        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
    }
}

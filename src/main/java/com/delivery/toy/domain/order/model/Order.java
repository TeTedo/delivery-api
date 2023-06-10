package com.delivery.toy.domain.order.model;

import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.global.common.domain.BaseTImeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;
import org.springframework.util.Assert;

@Getter
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order extends BaseTImeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne // 여러개의 Order -> 한개의 Food
    @JoinColumn(nullable = false, name = "foodId")
    private Food food;

    // @ManyToOne
    // JoinColumn(nullable = false, name = "user_id")
    // TODO user 도메인 추가 후 수정
    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private int count;

    @Builder
    public Order(Long id, Food food, int userId, int count) {

        Assert.notNull(food,"food는 필수입니다.");
        Assert.notNull(userId, "userId는 필수입니다.");
        Assert.isTrue(count > 0, "count는 0보다 커야합니다.");

        this.id = id;
        this.food = food;
        this.userId = userId;
        this.count = count;
    }
}

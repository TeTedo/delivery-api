package com.delivery.toy.domain.order.service;

import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.exception.FoodNotFoundException;
import com.delivery.toy.domain.food.mapper.FoodMapper;
import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import com.delivery.toy.domain.food.service.FoodService;
import com.delivery.toy.domain.food.service.FoodServiceImpl;
import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.request.OrderRequest;
import com.delivery.toy.domain.order.dto.response.CreateOrderResponse;
import com.delivery.toy.domain.order.mapper.OrderMapper;
import com.delivery.toy.domain.order.model.Order;
import com.delivery.toy.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public CreateOrderResponse saveOrder(CreateOrderRequest request){

        Food food = foodRepository.findById(request.foodId())
                .orElseThrow(()-> new FoodNotFoundException("해당 id를 가진 Food가 없습니다."));

        // TODO user 도메인 적용 후 userId 변경
        int userId =  1;

        OrderRequest orderRequest = OrderRequest.builder()
                .food(food)
                .userId(userId)
                .count(request.count())
                .build();

        Order order = orderMapper.toOrder(orderRequest);

        Order savedOrder = orderRepository.save(order);
        Assert.isTrue(savedOrder.equals(order), "order 저장 실패");

        boolean status = true;

        return orderMapper.toCreateOrderResponse(status);
    }
}

package com.delivery.toy.domain.order.service;

import com.delivery.toy.domain.food.exception.FoodNotFoundException;
import com.delivery.toy.domain.food.model.Food;
import com.delivery.toy.domain.food.repository.FoodRepository;
import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.request.OrderRequest;
import com.delivery.toy.domain.order.dto.response.OrderResponse;
import com.delivery.toy.domain.order.mapper.OrderMapper;
import com.delivery.toy.domain.order.model.Order;
import com.delivery.toy.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse saveOrder(CreateOrderRequest request){

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

        return orderMapper.toOrderResponse(savedOrder);
    }
}

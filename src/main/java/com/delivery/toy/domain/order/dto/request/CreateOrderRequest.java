package com.delivery.toy.domain.order.dto.request;

import lombok.Builder;

@Builder
public record CreateOrderRequest(Long foodId, int count) {
}

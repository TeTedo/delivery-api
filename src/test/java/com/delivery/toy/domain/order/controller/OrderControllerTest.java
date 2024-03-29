package com.delivery.toy.domain.order.controller;

import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.response.OrderResponse;
import com.delivery.toy.domain.order.service.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderServiceImpl orderServiceImpl;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        this.objectMapper = new ObjectMapper();
    }

    @DisplayName("POST /orders")
    @Test
    void post_orders() throws Exception {
        // given
        Long foodId = 1L;
        int count = 1;
        CreateOrderRequest request = getRequest(foodId, count);

        OrderResponse response = getResponse();

        Mockito.when(orderServiceImpl.saveOrder(request))
                .thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult);
    }

    private CreateOrderRequest getRequest(Long foodId, int count) {
        return CreateOrderRequest.builder()
                .foodId(foodId)
                .count(count)
                .build();
    }

    private OrderResponse getResponse() {
        return OrderResponse.builder()
                .build();
    }
}

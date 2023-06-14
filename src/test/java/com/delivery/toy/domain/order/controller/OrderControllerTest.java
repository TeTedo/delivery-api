package com.delivery.toy.domain.order.controller;

import com.delivery.toy.domain.order.dto.request.CreateOrderRequest;
import com.delivery.toy.domain.order.dto.response.CreateOrderResponse;
import com.delivery.toy.domain.order.service.OrderServiceImpl;
import com.google.gson.Gson;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderServiceImpl orderServiceImpl;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @DisplayName("POST /orders")
    @Test
    void save() throws Exception {
        // given
        Long foodId = 1L;
        int count = 1;
        CreateOrderRequest request = getRequest(foodId, count);

        boolean status = true;
        CreateOrderResponse response = getResponse(status);

        Mockito.when(orderServiceImpl.saveOrder(request))
                .thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", response.status()).exists())
                .andReturn();
        System.out.println(mvcResult);
    }

    private CreateOrderRequest getRequest(Long foodId, int count) {
        return CreateOrderRequest.builder()
                .foodId(foodId)
                .count(count)
                .build();
    }

    private CreateOrderResponse getResponse(boolean status) {
        return CreateOrderResponse.builder()
                .status(status)
                .build();
    }
}

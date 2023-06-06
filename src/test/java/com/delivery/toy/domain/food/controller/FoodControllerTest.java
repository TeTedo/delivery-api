package com.delivery.toy.domain.food.controller;

import com.delivery.toy.domain.food.dto.request.CreateFoodRequest;
import com.delivery.toy.domain.food.dto.request.FindByFoodIdRequest;
import com.delivery.toy.domain.food.dto.response.CreateFoodResponse;
import com.delivery.toy.domain.food.dto.response.FoodResponse;
import com.delivery.toy.domain.food.service.FoodServiceImpl;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FoodControllerTest {

    @Mock
    private FoodServiceImpl foodServiceImpl;

    @InjectMocks
    private FoodController foodController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(foodController).build();
    }

    @DisplayName("Post /foods")
    @Test
    void save() throws Exception{
        // given
        CreateFoodRequest request = createFoodRequest();
        CreateFoodResponse response = CreateFoodResponse
                .builder()
                .status(true)
                .build();

        Mockito.when(foodServiceImpl.save(request))
                .thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/foods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        //  then
        MvcResult mvcResult = resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("status",response.status()).exists())
                .andReturn();
    }

    @DisplayName("Get /foods/{food-id}")
    @Test
    void findById() throws Exception{
        // given
        Long id = 1L;
        FindByFoodIdRequest request = FindByFoodIdRequest
                .builder()
                .id(id)
                .build();
        FoodResponse response = foodResponse(id);


        Mockito.when(foodServiceImpl.findById(request))
                .thenReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/foods/" + id)
        );

        // then
        MvcResult mvcResult =  resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("id",id).exists())
                .andReturn();
    }

    private CreateFoodRequest createFoodRequest(){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";


        return CreateFoodRequest
                .builder()
                .name(name)
                .caloriePerGram(caloriePerGram)
                .carbohydratePerGram(carbohydratePerGram)
                .proteinPerGram(proteinPerGram)
                .provincePerGram(provincePerGram)
                .grams(grams)
                .price(price)
                .imgPath(imgPath)
                .build();
    }

    private FoodResponse foodResponse(Long id){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";


        return FoodResponse
                .builder()
                .id(id)
                .name(name)
                .caloriePerGram(caloriePerGram)
                .carbohydratePerGram(carbohydratePerGram)
                .proteinPerGram(proteinPerGram)
                .provincePerGram(provincePerGram)
                .grams(grams)
                .price(price)
                .imgPath(imgPath)
                .build();
    }
}
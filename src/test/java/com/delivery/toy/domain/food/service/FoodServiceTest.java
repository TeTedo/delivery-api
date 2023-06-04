package com.delivery.toy.domain.food.service;


import lombok.Builder;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class FoodServiceTest {
    private FoodService foodService;
    private Long foodId;
    private FoodMapper foodMapper;
    private FoodRepositoryImpl foodRepositoryImpl;

    @BeforeEach
    void setUp(){
        foodService= new FoodService();
        foodId = 0L;
        foodMapper = new FoodMapper();
        foodRepositoryImpl =  new FoodRepositoryImpl();
    }


    @DisplayName("FoodRequestDto를 받아Entity로 바꾸고 저장한 후 FoodResponseDto로 변환까지")
    @Test
    void saveFood(){
        String name = "salad";
        double caloriePerGram = 1.2;
        double carbohydratePerGram = 0.03;
        double proteinPerGram = 0.05;
        double provincePerGram = 0.01;
        int grams = 250;
        int price = 12000;
        String imgPath = "tempImgPath";

        CreateFoodRequest foodDto = new CreateFoodRequest(
                name,
                caloriePerGram,
                carbohydratePerGram,
                proteinPerGram,
                provincePerGram,
                grams,
                price,
                imgPath
        );

        Assertions.assertThat(foodDto).isNotNull();

        Food food = foodService.save(foodDto);

        Assertions.assertThat(food)
                .hasFieldOrPropertyWithValue("name","salad")
                .hasFieldOrPropertyWithValue("caloriePerGram",1.2)
                .hasFieldOrPropertyWithValue("carbohydratePerGram",0.03)
                .hasFieldOrPropertyWithValue("proteinPerGram",0.05)
                .hasFieldOrPropertyWithValue("provincePerGram",0.01)
                .hasFieldOrPropertyWithValue("grams",250)
                .hasFieldOrPropertyWithValue("price",12000)
                .hasFieldOrPropertyWithValue("imgPath","tempImgPath");

        FoodResponse foodResponse =  foodMapper.toFoodResponse(food);

        Assertions.assertThat(foodResponse)
                .hasFieldOrPropertyWithValue("name","salad")
                .hasFieldOrPropertyWithValue("caloriePerGram",1.2)
                .hasFieldOrPropertyWithValue("carbohydratePerGram",0.03)
                .hasFieldOrPropertyWithValue("proteinPerGram",0.05)
                .hasFieldOrPropertyWithValue("provincePerGram",0.01)
                .hasFieldOrPropertyWithValue("grams",250)
                .hasFieldOrPropertyWithValue("price",12000)
                .hasFieldOrPropertyWithValue("imgPath","tempImgPath");

    }

    @DisplayName("id를 받아 조회한다.")
    @Test
    void findById() {
        saveFood();
        FindByIdFoodRequest findByIdFoodRequest = new FindByIdFoodRequest(1L);
        Food food = foodService.findById(findByIdFoodRequest);

        Assertions.assertThat(food)
                .hasFieldOrPropertyWithValue("id",1L);

        FoodResponse foodResponse =  foodMapper.toFoodResponse(food);

        Assertions.assertThat(foodResponse)
                .hasFieldOrPropertyWithValue("id",1L);

    }




    private class FoodService {

        public Food save(CreateFoodRequest foodDto) {
            Food food = foodMapper.toFood(foodDto);
            Assert.notNull(food, "food는 null값이 될수 없습니다.");
            return foodRepositoryImpl.save(food);
        }

        public Food findById(FindByIdFoodRequest findByIdFoodRequest) {
            Food food =  foodRepositoryImpl.findById(findByIdFoodRequest.id);
            return food;
        }
    }

    @Getter
    @Builder
    private static class Food {
        private  final Long id;
        private  final String name;
        private  final double caloriePerGram;
        private  final double carbohydratePerGram;
        private  final double proteinPerGram;
        private  final double provincePerGram;
        private  final int grams;
        private  final int price;
        private  final String imgPath;

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
            this.id = ++id;
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

    @Builder
    private static record CreateFoodRequest(
            String name,
            double caloriePerGram,
            double carbohydratePerGram,
            double proteinPerGram,
            double provincePerGram,
            int grams,
            int price,
            String imgPath) {
    }

    @Builder
    private static record FoodResponse(
            Long id,
            String name,
            double caloriePerGram,
            double carbohydratePerGram,
            double proteinPerGram,
            double provincePerGram,
            int grams,
            int price,
            String imgPath) {
    }

    private class FoodMapper {
        public Food toFood(CreateFoodRequest createFoodRequest) {
            return Food.builder()
                    .id(foodId)
                    .name(createFoodRequest.name())
                    .caloriePerGram(createFoodRequest.caloriePerGram())
                    .carbohydratePerGram(createFoodRequest.carbohydratePerGram())
                    .proteinPerGram(createFoodRequest.proteinPerGram())
                    .provincePerGram(createFoodRequest.provincePerGram())
                    .grams(createFoodRequest.grams())
                    .price(createFoodRequest.price())
                    .imgPath(createFoodRequest.imgPath())
                    .build();
        }

        public FoodResponse toFoodResponse(Food food){
            return FoodResponse.builder()
                    .id(food.getId())
                    .name(food.getName())
                    .caloriePerGram(food.getCaloriePerGram())
                    .carbohydratePerGram(food.getCarbohydratePerGram())
                    .proteinPerGram(food.getProteinPerGram())
                    .provincePerGram(food.getProvincePerGram())
                    .grams(food.getGrams())
                    .price(food.getPrice())
                    .imgPath(food.getImgPath())
                    .build();
        }
    }

    private interface FoodRepository {
        Food save(Food food);
        Food findById(Long id);
    }

    private class FoodRepositoryImpl implements FoodRepository{
        private Food food;

        @Override
        public Food save(Food food) {
            this.food = food;
            return food;
        }

        @Override
        public Food findById(Long id) {
            if(this.food.id == id) return this.food;
            else throw new FoodIllegalException("존재하지 않는 id입니다.");
        }
    }

    private class FoodIllegalException extends IllegalArgumentException {
        public FoodIllegalException(String s) {
            super(s);
        }
    }

    private record FindByIdFoodRequest(Long id) {
    }

}

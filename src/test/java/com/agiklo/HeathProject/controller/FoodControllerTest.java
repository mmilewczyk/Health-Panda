package com.agiklo.HeathProject.controller;

import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.Food;
import com.agiklo.HeathProject.model.dto.FoodDTO;
import com.agiklo.HeathProject.model.enums.FOOD_CATEGORY;
import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.repository.FoodRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class FoodControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FoodRepository foodRepository;

    @Test
    void shouldNoGetFoodsAndReturnForbridden() throws Exception {
        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v2/foods"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        //then
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo("Access Denied");
    }

    @Test
    @Transactional
    @WithMockUser(username = "mateusz@excample.com", password = "123", authorities = "ADMIN")
    void shouldGetFoodById() throws Exception {
        //given
        Food fakeFood = prepareFoodToTest();
        foodRepository.save(fakeFood);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v2/foods/" + fakeFood.getId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        //then
        FoodDTO food = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), FoodDTO.class);
        assertThat(food).isNotNull();
        assertThat(food.getName()).isEqualTo("Tofu with vegetables");
        assertThat(food.getAuthor()).isEqualTo("mateusz@excample.com");
        assertThat(food.getContent()).isEqualTo("test");
    }

    @Test
    @WithMockUser(username = "mateusz@excample.com", password = "123", authorities = "ADMIN")
    void shouldNoGetFoodById() throws Exception {
        //given
        List<Food> foods = foodRepository.findAll();
        long fakeId;
        if (foods.isEmpty()) {
            fakeId = 1;}
        else {
            fakeId = foods.stream()
                    .mapToLong(Food::getId)
                    .max()
                    .orElseThrow(NoSuchElementException::new);
        }
        fakeId++;
        //when
        mockMvc.perform(get("/api/v2/foods/" + fakeId)).andDo(print())
                //then
                .andExpect(status().is(404))
                .andReturn();
    }

    Food prepareFoodToTest(){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setEmail("mateusz@excample.com");
        applicationUser.setRole(USER_ROLE.ADMIN);
        applicationUser.setPassword("123");

        Food food = new Food();
        food.setAuthor(applicationUser);
        food.setCategory(FOOD_CATEGORY.DINNER);
        food.setName("Tofu with vegetables");
        food.setContent("test");
        return food;
    }


}

package com.agiklo.HeathProject.controller;


import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.enums.EXERCISE_NAME;
import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.WorkoutRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    void shouldNoGetWorkoutsAndReturnForbridden() throws Exception {
        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v2/workouts"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();
        //then
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo("Access Denied");
    }

    @Test
    @Transactional
    @WithMockUser(username = "mateuszmilewczyk1@gmail.com", password = "123", authorities = "USER")
    void shouldGetWorkoutById() throws Exception {
        //given
        Workout fakeWorkout = prepareWorkoutToTest();
        workoutRepository.save(fakeWorkout);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/api/v2/workouts/" + fakeWorkout.getWorkoutId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        //then
        WorkoutDTO workoutDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), WorkoutDTO.class);
        assertThat(workoutDTO).isNotNull();
        assertThat(workoutDTO.getWorkoutName()).isEqualTo(EXERCISE_NAME.BENCH_PRESS.name());
    }

    @Test
    @WithMockUser(username = "mateuszmilewczyk1@gmail.com", password = "123", authorities = "USER")
    void shouldNoGetWorkoutById() throws Exception {
        //given
        List<Workout> workouts = workoutRepository.findAll();
        long fakeId;
        if (workouts.isEmpty()) {
            fakeId = 1;}
        else {
            fakeId = workouts.stream()
                    .mapToLong(Workout::getWorkoutId)
                    .max()
                    .orElseThrow(NoSuchElementException::new);
        }
        fakeId++;
        //when
        mockMvc.perform(get("/api/v2/workouts/" + fakeId)).andDo(print())
                //then
                .andExpect(status().is(404))
                .andReturn();
    }

    Workout prepareWorkoutToTest(){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setEmail("mateusz@excample.com");
        applicationUser.setRole(USER_ROLE.ADMIN);
        applicationUser.setPassword("123");

        Workout workout = new Workout();
        workout.setUser(applicationUser);
        workout.setWorkoutName(EXERCISE_NAME.BENCH_PRESS.name());
        return workout;
    }
}

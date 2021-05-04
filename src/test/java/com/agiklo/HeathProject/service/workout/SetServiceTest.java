package com.agiklo.HeathProject.service.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.agiklo.HeathProject.mapper.SetMapper;
import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.dto.SetDTO;
import com.agiklo.HeathProject.model.enums.EXERCISE_NAME;
import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.model.workout.Exercise;
import com.agiklo.HeathProject.model.workout.Set;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.ExerciseRepository;
import com.agiklo.HeathProject.repository.SetRepository;
import com.sun.security.auth.UserPrincipal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {SetService.class})
@ExtendWith(SpringExtension.class)
class SetServiceTest {
    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private SetMapper setMapper;

    @MockBean
    private SetRepository setRepository;

    @Autowired
    private SetService setService;

    @Test
    void shouldGetEmptyListOfAllByExercises() {
        // when
        when(this.setRepository.getAllByExercise_ExerciseId(any(), any()))
                .thenReturn(new ArrayList<>());

        // then
        assertTrue(this.setService.getAllByExercises(123L, null).isEmpty());
        verify(this.setRepository).getAllByExercise_ExerciseId(any(), any());
    }

    @Test
    void shouldGetOneOfAllByExercises() {
        // given
        Set set = createSet();
        ArrayList<Set> setList = new ArrayList<>();
        setList.add(set);

        // when
        when(this.setRepository.getAllByExercise_ExerciseId(any(), any())).thenReturn(setList);
        when(this.setMapper.mapSetToDTO(any())).thenReturn(new SetDTO());

        // then
        assertEquals(1, this.setService.getAllByExercises(123L, null).size());
        verify(this.setMapper).mapSetToDTO(any());
        verify(this.setRepository).getAllByExercise_ExerciseId(any(), any());
    }

    @Test
    void shouldAddNewSet() {
        // given
        Set set = createSet();
        Exercise exercise = createExercise();
        Optional<Exercise> ofResult = Optional.of(exercise);

        // when
        when(this.setRepository.save(any())).thenReturn(set);
        when(this.exerciseRepository.findById(any())).thenReturn(ofResult);

        // then
        assertSame(set, this.setService.addNewSet(123L, new Set()));
        verify(this.exerciseRepository).findById(any());
        verify(this.setRepository).save(any());
    }

    @Test
    void shouldNotAddNewSetAndThrowsException() {
        // given
        Set set = createSet();

        // when
        when(this.setRepository.save(any())).thenReturn(set);
        when(this.exerciseRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(ResponseStatusException.class, () -> this.setService.addNewSet(123L, new Set()));
        verify(this.exerciseRepository).findById(any());
    }

    @Test
    void shouldDeleteSetById() {
        // given
        Set set = createSet();
        Optional<Set> ofResult = Optional.of(set);

        // when
        doNothing().when(this.setRepository).deleteById(any());
        when(this.setRepository.findById(any())).thenReturn(ofResult);
        this.setService.deleteSetById(123L, new UserPrincipal("mati@healthpanda.com"));

        // then
        verify(this.setRepository).deleteById(any());
        verify(this.setRepository).findById(any());
    }

    @Test
    void shouldNotDeleteSetByIdAndThrowsException() {
        // when
        doNothing().when(this.setRepository).deleteById(any());
        when(this.setRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.setService.deleteSetById(123L, new UserPrincipal("mati@healthpanda.com")));
        verify(this.setRepository).findById(any());
    }

    private ApplicationUser createApplicationUser(){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setPassword("test");
        applicationUser.setEmail("mati@healthpanda.com");
        applicationUser.setWorkout(new ArrayList<>());
        applicationUser.setIsEnabled(true);
        applicationUser.setRole(USER_ROLE.USER);
        applicationUser.setId(123L);
        applicationUser.setIsLocked(true);
        return applicationUser;
    }

    private Workout createWorkout(){
        Workout workout = new Workout();
        workout.setDateOfWorkout(LocalDateTime.of(1, 1, 1, 1, 1));
        workout.setExercise(new ArrayList<>());
        workout.setWorkoutName("Friday Workout");
        workout.setUser(createApplicationUser());
        workout.setWorkoutId(123L);
        return workout;
    }

    private Exercise createExercise(){
        Exercise exercise = new Exercise();
        exercise.setWorkout(createWorkout());
        exercise.setExerciseName(EXERCISE_NAME.AROUND_THE_WORLD);
        exercise.setSet(new ArrayList<>());
        exercise.setExerciseId(123L);
        return exercise;
    }

    private Set createSet(){
        Set set = new Set();
        set.setWeight(10.0);
        set.setReps(1);
        set.setSetId(123L);
        set.setExercise(createExercise());
        return set;
    }
}


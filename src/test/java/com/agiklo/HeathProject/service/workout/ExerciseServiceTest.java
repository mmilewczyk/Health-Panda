package com.agiklo.HeathProject.service.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.agiklo.HeathProject.mapper.ExerciseMapper;
import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.dto.ExerciseDTO;
import com.agiklo.HeathProject.model.enums.EXERCISE_NAME;
import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.model.workout.Exercise;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.ExerciseRepository;
import com.agiklo.HeathProject.repository.WorkoutRepository;
import com.sun.security.auth.UserPrincipal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {ExerciseService.class})
@ExtendWith(SpringExtension.class)
class ExerciseServiceTest {
    @MockBean
    private ExerciseMapper exerciseMapper;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseService exerciseService;

    @MockBean
    private WorkoutRepository workoutRepository;

    @Test
    @Disabled
    // TODO: Finish that test
    void shouldAddNewExercise() {
        // given
        Exercise exercise = createExercise();

        // when
        when(this.exerciseRepository.save(any())).thenReturn(exercise);

        // then
        assertEquals(exercise, this.exerciseService.addNewExercise(123L, new Exercise(EXERCISE_NAME.AROUND_THE_WORLD)));
        verify(this.exerciseRepository).save(any());
        verify(this.workoutRepository).save(any());
        verify(this.workoutRepository).findById(any());
    }

    @Test
    void shouldNotAddNewExerciseAndThrowsException() {
        // given
        Workout workout = createWorkout();

        // when
        when(this.workoutRepository.save(any())).thenReturn(workout);
        when(this.workoutRepository.findById(any())).thenReturn(Optional.empty());
        when(this.exerciseRepository.save(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.exerciseService.addNewExercise(123L, new Exercise(EXERCISE_NAME.AROUND_THE_WORLD)));
        verify(this.workoutRepository).findById(any());
    }

    @Test
    void shouldGetEmptyListOfAllByWorkoutId() {
        // when
        when(this.exerciseRepository.getAllByWorkout_WorkoutId(any(), any()))
                .thenReturn(new ArrayList<>());

        // then
        assertTrue(this.exerciseService.getAllByWorkoutId(123L, null).isEmpty());
        verify(this.exerciseRepository).getAllByWorkout_WorkoutId(any(), any());
    }

    @Test
    void shouldGetAllByWorkoutId() {
        // given
        Exercise exercise = createExercise();
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise);

        // when
        when(this.exerciseRepository.getAllByWorkout_WorkoutId(any(), any())).thenReturn(exerciseList);
        when(this.exerciseMapper.mapExerciseToDTO(any())).thenReturn(new ExerciseDTO());

        // then
        assertEquals(1, this.exerciseService.getAllByWorkoutId(123L, null).size());
        verify(this.exerciseMapper).mapExerciseToDTO(any());
        verify(this.exerciseRepository).getAllByWorkout_WorkoutId(any(), any());
    }

    @Test
    void shouldNotDeleteExistingExerciseByIdAndThrowsException() {
        // given
        Exercise exercise = createExercise();
        Optional<Exercise> ofResult = Optional.of(exercise);

        // when
        when(this.exerciseRepository.findById(any())).thenReturn(ofResult);

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.exerciseService.deleteExerciseById(123L, new UserPrincipal("principal")));
        verify(this.exerciseRepository).findById(any());
    }

    @Test
    void shouldDeleteExerciseById() {
        // given
        Exercise exercise = createExercise();
        Optional<Exercise> ofResult = Optional.of(exercise);

        // when
        doNothing().when(this.exerciseRepository).deleteById(any());
        when(this.exerciseRepository.findById(any())).thenReturn(ofResult);
        this.exerciseService.deleteExerciseById(123L, new UserPrincipal("mati@healthpanda.com"));

        // then
        verify(this.exerciseRepository).deleteById(any());
        verify(this.exerciseRepository).findById(any());
    }

    @Test
    void shouldNotDeleteNotExistingExerciseByIdAndThrowsException() {
        // when
        doNothing().when(this.exerciseRepository).deleteById(any());
        when(this.exerciseRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.exerciseService.deleteExerciseById(123L, new UserPrincipal("principal")));
        verify(this.exerciseRepository).findById(any());
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
}


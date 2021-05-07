package com.agiklo.HeathProject.service.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.agiklo.HeathProject.mapper.WorkoutMapper;
import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import com.agiklo.HeathProject.repository.WorkoutRepository;
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

@ContextConfiguration(classes = {WorkoutService.class})
@ExtendWith(SpringExtension.class)
class WorkoutServiceTest {
    @MockBean
    private ApplicationUserRepository applicationUserRepository;

    @MockBean
    private WorkoutMapper workoutMapper;

    @MockBean
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutService workoutService;

    @Test
    void shouldGetEmptyListOfAllWorkouts() {
        // given
        ApplicationUser applicationUser = createApplicationUser();
        Optional<ApplicationUser> ofResult = Optional.of(applicationUser);

        // when
        when(this.workoutRepository.findWorkoutsByUser(any(), any()))
                .thenReturn(new ArrayList<>());
        when(this.applicationUserRepository.findByEmail(anyString())).thenReturn(ofResult);

        // then
        assertTrue(this.workoutService.getAllWorkouts(new UserPrincipal("mati@healthpanda.com"), null).isEmpty());
        verify(this.applicationUserRepository).findByEmail(anyString());
        verify(this.workoutRepository).findWorkoutsByUser(any(), any());
    }

    @Test
    void shouldMapAndGetAllWorkouts() {
        // given
        Workout workout = createWorkout();
        ArrayList<Workout> workoutList = new ArrayList<>();
        workoutList.add(workout);
        ApplicationUser applicationUser = createApplicationUser();
        Optional<ApplicationUser> ofResult = Optional.of(applicationUser);

        // when
        when(this.workoutRepository.findWorkoutsByUser(any(), any())).thenReturn(workoutList);
        when(this.workoutMapper.mapWorkoutToDTO(any())).thenReturn(new WorkoutDTO());
        when(this.applicationUserRepository.findByEmail(anyString())).thenReturn(ofResult);

        // then
        assertEquals(1, this.workoutService.getAllWorkouts(new UserPrincipal("mati@healthpanda.com"), null).size());
        verify(this.applicationUserRepository).findByEmail(anyString());
        verify(this.workoutMapper).mapWorkoutToDTO(any());
        verify(this.workoutRepository).findWorkoutsByUser(any(), any());
    }

    @Test
    void shouldNotGetAllWorkoutsAndThrowException() {
        // given
        Workout workout = createWorkout();
        ArrayList<Workout> workoutList = new ArrayList<>();
        workoutList.add(workout);

        // when
        when(this.workoutRepository.findWorkoutsByUser(any(), any())).thenReturn(workoutList);
        when(this.workoutMapper.mapWorkoutToDTO(any())).thenReturn(new WorkoutDTO());
        when(this.applicationUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.workoutService.getAllWorkouts(new UserPrincipal("mati@healthpanda.com"), null));
        verify(this.applicationUserRepository).findByEmail(anyString());
    }

    @Test
    void shouldAddNewWorkout() {
        // given
        ApplicationUser applicationUser = createApplicationUser();
        Workout workout = createWorkout();
        Optional<ApplicationUser> ofResult = Optional.of(applicationUser);

        // when
        when(this.workoutRepository.save(any())).thenReturn(workout);
        when(this.applicationUserRepository.findByEmail(anyString())).thenReturn(ofResult);

        // then
        assertSame(workout, this.workoutService.addNewWorkout(workout, new UserPrincipal("mati@healthpanda.com")));
        verify(this.applicationUserRepository).findByEmail(anyString());
        verify(this.workoutRepository).save(any());
    }

    @Test
    void shouldNotAddNewWorkoutAndThrowsException() {
        // given
        Workout workout = new Workout();
        Workout workout1 = new Workout();

        // when
        when(this.workoutRepository.save(any())).thenReturn(workout);
        when(this.applicationUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.workoutService.addNewWorkout(workout1, new UserPrincipal("mati@healthpanda.com")));
        verify(this.applicationUserRepository).findByEmail(anyString());
    }

    @Test
    void shouldAddNewWorkoutWithSettingUpDateAndUser() {
        // given
        Workout workout = createWorkout();
        ApplicationUser applicationUser = createApplicationUser();
        Optional<ApplicationUser> ofResult = Optional.of(applicationUser);
        Workout workout1 = mock(Workout.class);

        // when
        when(this.workoutRepository.save(any())).thenReturn(workout);
        when(this.applicationUserRepository.findByEmail(anyString())).thenReturn(ofResult);
        doNothing().when(workout1).setDateOfWorkout(any());
        doNothing().when(workout1).setUser(any());

        // then
        assertSame(workout, this.workoutService.addNewWorkout(workout1, new UserPrincipal("mati@healthpanda.com")));
        verify(this.applicationUserRepository).findByEmail(anyString());
        verify(workout1).setDateOfWorkout(any());
        verify(workout1).setUser(any());
        verify(this.workoutRepository).save(any());
    }

    @Test
    void shouldDeleteWorkoutById() {
        // given
        Workout workout = createWorkout();
        Optional<Workout> ofResult = Optional.of(workout);

        // when
        doNothing().when(this.workoutRepository).deleteById(any());
        when(this.workoutRepository.findById(any())).thenReturn(ofResult);
        this.workoutService.deleteWorkoutById(123L, new UserPrincipal("mati@healthpanda.com"));

        // then
        verify(this.workoutRepository).deleteById(any());
        verify(this.workoutRepository).findById(any());
    }

    @Test
    void shouldNotDeleteWorkoutByIdAndThrowsException() {
        // when
        doNothing().when(this.workoutRepository).deleteById(any());
        when(this.workoutRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThrows(ResponseStatusException.class,
                () -> this.workoutService.deleteWorkoutById(123L, new UserPrincipal("mati@healthpanda.com")));
        verify(this.workoutRepository).findById(any());
    }

    private ApplicationUser createApplicationUser() {
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

    private Workout createWorkout() {
        Workout workout = new Workout();
        workout.setDateOfWorkout(LocalDateTime.of(1, 1, 1, 1, 1));
        workout.setExercise(new ArrayList<>());
        workout.setWorkoutName("Friday Workout");
        workout.setUser(createApplicationUser());
        workout.setWorkoutId(123L);
        return workout;
    }
}


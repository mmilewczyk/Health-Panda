package com.agiklo.HeathProject.service.workout;

import com.agiklo.HeathProject.mapper.WorkoutMapper;
import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import com.agiklo.HeathProject.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final ApplicationUserRepository applicationUserRepository;

    public List<WorkoutDTO> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutMapper::mapWorkoutToDTO)
                .collect(Collectors.toList());
    }

    public Workout addNewWorkout(Workout workout, Principal principal) {
        ApplicationUser user = applicationUserRepository.findByEmail(principal.getName()).orElseThrow(() ->
                new IllegalStateException("User not found"));
        LocalDateTime actualTime = LocalDateTime.now();
        workout.setUser(user);
        workout.setDateOfWorkout(actualTime);
        return workoutRepository.save(workout);
    }
}

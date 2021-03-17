package com.agiklo.HeathProject.service.workout;

import com.agiklo.HeathProject.mapper.ExerciseMapper;
import com.agiklo.HeathProject.model.dto.ExerciseDTO;
import com.agiklo.HeathProject.model.workout.Exercise;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.ExerciseRepository;
import com.agiklo.HeathProject.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseMapper exerciseMapper;

    public Exercise addNewExercise(Long id, Exercise exercise) {
        Workout workout = workoutRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find specify workout"));
        exercise.setWorkout(workout);
        workoutRepository.save(workout);
        return exerciseRepository.save(exercise);
    }

    public List<ExerciseDTO> getAllByWorkoutId(Long id) {
        return exerciseRepository.getAllByWorkout_WorkoutId(id)
                .stream()
                .map(exerciseMapper::mapExerciseToDTO)
                .collect(Collectors.toList());
    }
}

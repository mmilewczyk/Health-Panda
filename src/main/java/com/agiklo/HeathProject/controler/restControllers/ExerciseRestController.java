package com.agiklo.HeathProject.controler.restControllers;

import com.agiklo.HeathProject.model.dto.ExerciseDTO;
import com.agiklo.HeathProject.model.workout.Exercise;
import com.agiklo.HeathProject.service.workout.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v2/workouts/exercises")
@AllArgsConstructor
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ExerciseDTO>> getAllExercises(@PathVariable("id") Long id) {
        return status(HttpStatus.OK).body(exerciseService.getAllByWorkoutId(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Exercise> addNewExercise(@PathVariable("id") Long id, @RequestBody Exercise exercise) {
        return status(HttpStatus.CREATED).body(exerciseService.addNewExercise(id, exercise));
    }
}

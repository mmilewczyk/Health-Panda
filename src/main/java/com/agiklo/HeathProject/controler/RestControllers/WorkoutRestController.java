package com.agiklo.HeathProject.controler.RestControllers;

import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController("/api/v2/workouts")
public class WorkoutRestController {

    private final WorkoutRepository trainingRepository;

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts(){
        return status(HttpStatus.OK).body(trainingRepository.findAll());
    }

}

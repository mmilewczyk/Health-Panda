package com.agiklo.HeathProject.controler.restControllers;

import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.service.workout.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.agiklo.HeathProject.controler.ApiMapping.WORKOUTS_REST_URL;
import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping(WORKOUTS_REST_URL)
public class WorkoutRestController {

    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<WorkoutDTO>> getAllWorkouts(){
        return status(HttpStatus.OK).body(workoutService.getAllWorkouts());
    }

}

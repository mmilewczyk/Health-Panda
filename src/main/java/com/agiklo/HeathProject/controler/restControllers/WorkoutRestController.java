package com.agiklo.HeathProject.controler.restControllers;

import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.service.workout.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.agiklo.HeathProject.controler.ApiMapping.WORKOUTS_REST_URL;
import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping(WORKOUTS_REST_URL)
public class WorkoutRestController {

    private final WorkoutService workoutService;

    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<WorkoutDTO>> getAllWorkouts(Pageable pageable) {
        return status(HttpStatus.OK).body(workoutService.getAllWorkouts(pageable));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Workout> addNewWorkout(@RequestBody Workout workout, Principal principal) {
        return status(HttpStatus.CREATED).body(workoutService.addNewWorkout(workout, principal));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutById(@PathVariable("id")Long id, Principal principal){
        workoutService.deleteWorkoutById(id, principal);
    }

}

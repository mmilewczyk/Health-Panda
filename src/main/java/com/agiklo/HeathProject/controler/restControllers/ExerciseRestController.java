package com.agiklo.HeathProject.controler.restControllers;

import com.agiklo.HeathProject.model.dto.ExerciseDTO;
import com.agiklo.HeathProject.model.workout.Exercise;
import com.agiklo.HeathProject.service.workout.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.agiklo.HeathProject.controler.ApiMapping.EXERCISES_REST_URL;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(EXERCISES_REST_URL)
@AllArgsConstructor
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<ExerciseDTO>> getAllExercises(@PathVariable("id") Long id, Pageable pageable) {
        return status(HttpStatus.OK).body(exerciseService.getAllByWorkoutId(id, pageable));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Exercise> addNewExercise(@PathVariable("id") Long id, @RequestBody Exercise exercise) {
        return status(HttpStatus.CREATED).body(exerciseService.addNewExercise(id, exercise));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExerciseById(@PathVariable("id")Long id, Principal principal){
        exerciseService.deleteExerciseById(id, principal);
    }
}

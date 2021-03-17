package com.agiklo.HeathProject.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutDTO {

    private Long id;
    private String date;
    private String workoutName;
    private List<ExerciseDTO> exercises;

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercise) {
        this.exercises = exercise;
    }

    public void addExercises(ExerciseDTO exerciseDTO) {
        if (exercises == null) {
            exercises = new ArrayList<>();
        }
        exercises.add(exerciseDTO);
    }
}

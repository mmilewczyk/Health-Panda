package com.agiklo.HeathProject.model.dto;

import com.agiklo.HeathProject.model.workout.Exercise;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutDTO {

    private Long id;
    private String date;
    private String workoutName;
    private List<Exercise> exercises;
}

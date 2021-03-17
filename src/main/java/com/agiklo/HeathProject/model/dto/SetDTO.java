package com.agiklo.HeathProject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetDTO {

    private String workoutName;
    private String exerciseName;
    private String date;
    private Long id;
    private Double weight;
    private Integer reps;

}

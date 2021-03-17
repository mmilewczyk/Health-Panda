package com.agiklo.HeathProject.mapper;

import com.agiklo.HeathProject.model.dto.ExerciseDTO;
import com.agiklo.HeathProject.model.workout.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    @Mapping(target = "workout", source = "workout.workoutName")
    @Mapping(target = "date", source = "workout.dateOfWorkout")
    ExerciseDTO mapExerciseToDTO(Exercise exercise);
}

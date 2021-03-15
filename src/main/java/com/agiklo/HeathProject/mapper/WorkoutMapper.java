package com.agiklo.HeathProject.mapper;

import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.workout.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    @Mapping(target = "id", source = "workoutId")
    @Mapping(target = "date", source = "dateOfWorkout")
    @Mapping(target = "workoutName", source = "workoutName")
    WorkoutDTO mapWorkoutToDTO(Workout workout);
}

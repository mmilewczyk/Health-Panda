package com.agiklo.HeathProject.mapper;

import com.agiklo.HeathProject.model.dto.SetDTO;
import com.agiklo.HeathProject.model.workout.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SetMapper {

    @Mapping(target = "workoutName", source = "exercise.workout.workoutName")
    @Mapping(target = "exerciseName", source = "exercise.exerciseName")
    @Mapping(target = "date", source = "exercise.workout.dateOfWorkout")
    @Mapping(target = "id", source = "setId")
    SetDTO mapSetToDTO(Set set);
}

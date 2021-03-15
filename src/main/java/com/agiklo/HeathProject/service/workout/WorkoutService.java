package com.agiklo.HeathProject.service.workout;

import com.agiklo.HeathProject.mapper.WorkoutMapper;
import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public List<WorkoutDTO> getAllWorkouts(){
        return workoutRepository.findAll()
                .stream()
                .map(workoutMapper::mapWorkoutToDTO)
                .collect(Collectors.toList());
    }

}

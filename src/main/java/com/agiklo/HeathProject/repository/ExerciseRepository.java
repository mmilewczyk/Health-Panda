package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.workout.Exercise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> getAllByWorkout_WorkoutId(Long id, Pageable pageable);
}

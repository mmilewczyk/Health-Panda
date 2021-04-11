package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.workout.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {

    List<Set> getAllByExercise_ExerciseId(Long id, Pageable pageable);
}

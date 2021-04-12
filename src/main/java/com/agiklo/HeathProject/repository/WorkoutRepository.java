package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.workout.Workout;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findWorkoutsByUser(ApplicationUser applicationUser, Pageable pageable);
}

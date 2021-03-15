package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.workout.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
}

package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    Training findByDate(String date);
    Training findByExercise(String name);
}

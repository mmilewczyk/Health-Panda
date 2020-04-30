package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training addWorkout(Training training){
        return trainingRepository.saveAndFlush(training);
    }

    public Iterable<Training> allWorkouts(){
        return trainingRepository.findAll();
    }

}

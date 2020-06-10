package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class TrainingService {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training addNewWorkout(Training training){
        return trainingRepository.saveAndFlush(training);
    }
    public Training editWorkout(@RequestBody Training training, @PathVariable Long id){
        trainingRepository.deleteById(id);
        Training trainingToUpdate = training;
        return trainingRepository.save(trainingToUpdate);
//                .map(training1 -> {
//                    training.setDate(training.getDate());
//                    training.setExercise(training.getExercise());
//                    training.setSeries(training.getSeries());
//                    training.setAmount(training.getAmount());
//                    return trainingRepository.save(training);
//                })
//                .orElseGet(() -> {
//                    training.setId(id);
//                    return trainingRepository.save(training);
//                });
    }

    public List<Training> findAllWorkouts(){
        return trainingRepository.findAll();
    }

    public void deleteWorkout(Long id){
        trainingRepository.deleteById(id);
    }

    public List<Training> findAllSortedByDateNewer(String date){
        return trainingRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    public List<Training> findAllSortedByDateOlder(String date){
        return trainingRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
    }

    public List<Training> findAllSortedByAmountMost(String amount){
        return trainingRepository.findAll(Sort.by(Sort.Direction.ASC, "amount"));
    }

    public List<Training> findAllSortedByAmountLeast(String amount){
        return trainingRepository.findAll(Sort.by(Sort.Direction.DESC, "amount"));
    }
}

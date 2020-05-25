package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrainingController {
    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @RequestMapping(value="/workout", method = RequestMethod.POST)
    public String addWorkout(@RequestBody Training training, Model model ){
        model.addAttribute("result", trainingRepository.saveAndFlush(training));
        return "workout";
    }

    @GetMapping("/workout")
    public Iterable<Training> showWorkouts(){
        return trainingRepository.findAll();
    }

    @RequestMapping(path = "/workout", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id){
        trainingRepository.deleteById(id);
    }
}

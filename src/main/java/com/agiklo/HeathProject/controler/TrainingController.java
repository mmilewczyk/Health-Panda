package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.BMR;
import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrainingController {
    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @RequestMapping("/addworkout")
    public String getBmrPage(Training training, Model model){
        model.addAttribute("training", training);
        return "addworkout";
    }
    @RequestMapping(value="/addworkout", method = RequestMethod.POST)
    public String addWorkout(@RequestBody Training training, Model model ){
        model.addAttribute("result", trainingRepository.saveAndFlush(training));
        return "addworkout";
    }

    @GetMapping("/workout")
    @ResponseBody
    public Iterable<Training> showWorkouts(){
        return trainingRepository.findAll();
    }

    @RequestMapping(path = "/workout", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id){
        trainingRepository.deleteById(id);
    }
}

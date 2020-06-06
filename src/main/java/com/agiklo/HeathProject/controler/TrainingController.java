package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class TrainingController {

    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @RequestMapping(value = "/addworkout", method = RequestMethod.GET)
    public String getAddWorkoutPage(Training training, Model model){
        model.addAttribute("training", training);
        return "addworkout";
    }
    @RequestMapping(value="/addworkout", method = RequestMethod.POST)
    public String addWorkout(Training training, Model model ){
        model.addAttribute("result", trainingRepository.saveAndFlush(training));
        return "redirect:/workout";
    }

    @RequestMapping(value = "/workout", method = RequestMethod.GET)
    public String showWorkouts(Training training, Model model){
        List<Training> workoutList = trainingRepository.findAll();
        model.addAttribute("trainings", workoutList);
        return "workout";
    }
    @RequestMapping(value = "/workout/update")
    public String update(@RequestParam Long id, Training training, Model model) {
        trainingRepository.deleteById(id);
        model.addAttribute("result", trainingRepository.saveAndFlush(training));
        return "addworkout";
    }

    @RequestMapping(value = "/workout/delete")
    public String delete(@RequestParam Long id){
        trainingRepository.deleteById(id);
        return "redirect:/workout";
    }
}

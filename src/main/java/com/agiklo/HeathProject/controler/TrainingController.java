//package com.agiklo.HeathProject.controler;
//
//import com.agiklo.HeathProject.model.workout.Workout;
//import com.agiklo.HeathProject.service.TrainingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class TrainingController {
//
//    private TrainingService trainingService;
//
//    @Autowired
//    public TrainingController(TrainingService trainingService) {
//        this.trainingService = trainingService;
//    }
////
//    @RequestMapping(value = "/workout/add-new", method = RequestMethod.GET)
//    public String getAddWorkoutPage(Workout training, Model model){
//        model.addAttribute("training", training);
//        return "addworkout";
//    }
//    @RequestMapping(value="/workout/add-new", method = RequestMethod.POST)
//    public String addWorkout(Workout training, Model model ){
//        model.addAttribute("result", trainingService.addNewWorkout(training));
//        return "redirect:/workout";
//    }
//
//    @RequestMapping(value = "/workout/sorted-by-date-newer", method = RequestMethod.GET)
//    public String workoutsSortedByDateNewer(Model model, String date){
//        List<Workout> workoutList = trainingService.findAllSortedByDateNewer(date);
//        model.addAttribute("trainings", workoutList);
//        return "workout";
//    }
//
//    @RequestMapping(value = "/workout/sorted-by-date-older", method = RequestMethod.GET)
//    public String workoutsSortedByDateOlder(Model model, String date){
//        List<Workout> workoutList = trainingService.findAllSortedByDateOlder(date);
//        model.addAttribute("trainings", workoutList);
//        return "workout";
//    }
//
//    @RequestMapping(value = "/workout/sorted-by-amount-most", method = RequestMethod.GET)
//    public String workoutsSortedByAmountMost(Model model, String amount) {
//        List<Workout> workoutList = trainingService.findAllSortedByAmountMost(amount);
//        model.addAttribute("trainings", workoutList);
//        return "workout";
//    }
//
//    @RequestMapping(value = "/workout/sorted-by-amount-least", method = RequestMethod.GET)
//    public String workoutsSortedByAmountLeast(Model model, String amount) {
//        List<Workout> workoutList = trainingService.findAllSortedByAmountLeast(amount);
//        model.addAttribute("trainings", workoutList);
//        return "workout";
//    }
//
//    @RequestMapping(value = "/workout", method = RequestMethod.GET)
//    public String showWorkouts(Model model, Workout training){
//        List<Workout> workoutList = trainingService.findAllWorkouts();
//        model.addAttribute("trainings", workoutList);
//        return "workout";
//    }
//
//    @RequestMapping(value = "/workout/update")
//    public String updateWorkout(@RequestParam Long id, Workout training, Model model) {
//        model.addAttribute("result", trainingService.editWorkout(training, id));
//        return "addworkout";
//    }
//
//    @RequestMapping(value = "/workout/delete")
//    public String delete(@RequestParam Long id){
//        trainingService.deleteWorkout(id);
//        return "redirect:/workout";
//    }
//}

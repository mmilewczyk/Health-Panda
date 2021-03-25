package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.dto.WorkoutDTO;
import com.agiklo.HeathProject.model.workout.Workout;
import com.agiklo.HeathProject.service.workout.WorkoutService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class TrainingController {

    private final WorkoutService workoutService;

    @GetMapping(value = "/workout/add-new")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String getAddWorkoutPage(Workout workout, Model model){
        model.addAttribute("training", workout);
        return "addworkout";
    }
    @PostMapping(value="/workout/add-new")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addWorkout(Workout workout, Model model, Principal principal){
        model.addAttribute("result", workoutService.addNewWorkout(workout, principal));
        return "redirect:/workout";
    }

    @GetMapping(value = "/workout")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String showWorkouts(Model model){
        List<WorkoutDTO> workoutList = workoutService.getAllWorkouts();
        model.addAttribute("trainings", workoutList);
        return "workout";
    }

    @RequestMapping(value = "/workout/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String delete(@PathVariable("id") Long id, Principal principal){
        workoutService.deleteWorkoutById(id, principal);
        return "redirect:/workout";
    }
}

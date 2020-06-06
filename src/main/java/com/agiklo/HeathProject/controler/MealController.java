package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.Meal;
import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MealController {


    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @RequestMapping(value = "/addmeal", method = RequestMethod.GET)
    public String getAddMealPage(Meal meal, Model model){
        model.addAttribute("meal", meal);
        return "addmeal";
    }
    @RequestMapping(value="/addmeal", method = RequestMethod.POST)
    public String addMeal(Meal meal, Model model){
        model.addAttribute("result", mealService.addMeal(meal));
        return "redirect:/meal";
    }

    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    public String showMeals(Meal meal, Model model){
        Iterable<Meal> mealList = mealService.allMeals();
        model.addAttribute("meals", mealList);
        return "meal";
    }

//    @RequestMapping(value = "/meal", method = RequestMethod.GET)
//    public String showMealByName(Meal meal, Model model, String name){
//        Iterable<Meal> mealList = mealService.findByName(name);
//        model.addAttribute("meals", mealList);
//        return "meal";
//    }
//
//    @RequestMapping(value = "/meal", method = RequestMethod.GET)
//    public String showMealByTime(Meal meal, Model model, double time){
//        Iterable<Meal> mealList = mealService.findByTime(time);
//        model.addAttribute("meals", mealList);
//        return "meal";
//    }

    @RequestMapping(value = "/meal/update")
    public String update(@RequestParam Long id, Meal meal, Model model) {
        mealService.delete(id);
        model.addAttribute("result", mealService.addMeal(meal));
        return "addmeal";
    }

    @RequestMapping(value = "/meal/delete")
    public String delete(@RequestParam Long id){
        mealService.delete(id);
        return "redirect:/meal";
    }
}

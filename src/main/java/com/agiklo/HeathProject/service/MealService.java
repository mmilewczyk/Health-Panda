package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.Meal;
import com.agiklo.HeathProject.model.Training;
import com.agiklo.HeathProject.repository.MealRepository;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MealService {

    private MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> findByName(String name){
        Meal meal = mealRepository.findByName(name);
        return findByName(meal.getName());
    }

    public List<Meal> findByTime(int time){
        Meal meal = mealRepository.findByTime(time);
        return findByTime(meal.getTime());
    }

    public Meal addMeal(Meal meal) {
        try {
                return mealRepository.saveAndFlush(meal);
        }catch (Exception e){
            e.getMessage().replaceAll("Treść jest zbyt długa", "Treść jest zbyt długa");
            return meal;
        }
    }

    public Iterable<Meal> allMeals(){
        return mealRepository.findAll();
    }

    public void delete(Long id){
        mealRepository.deleteById(id);
    }

    public Meal editMeal(@RequestBody Meal meal, @PathVariable Long id){
        Meal mealToUpdate = meal;
        return mealRepository.save(mealToUpdate);
    }

    public List<Meal> findAllSortedByTimeMost(String time){
        return mealRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
    }

    public List<Meal> findAllSortedByTimeLeast(String time){
        return mealRepository.findAll(Sort.by(Sort.Direction.ASC, "time"));
    }
}

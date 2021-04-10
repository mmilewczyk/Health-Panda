package com.agiklo.HeathProject.controler.restControllers;

import com.agiklo.HeathProject.model.Food;
import com.agiklo.HeathProject.model.dto.FoodDTO;
import com.agiklo.HeathProject.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.agiklo.HeathProject.controler.ApiMapping.FOODS_REST_URL;
import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping(FOODS_REST_URL)
public class FoodRestController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getFoods(Pageable pageable){
        return status(HttpStatus.OK).body(foodService.getAllFoods(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable("id") Long id){
        return status(HttpStatus.OK).body(foodService.getFoodById(id));
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<FoodDTO>> getAllUserFoodsById(@PathVariable("id") Long id){
        return status(HttpStatus.OK).body(foodService.getAllUserFoodsById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Food addNewFood(@RequestBody Food food, Principal principal){
        return foodService.addNewFood(food, principal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodById(@PathVariable("id") Long id, Principal principal){
        foodService.deleteFoodById(id, principal);
    }
}

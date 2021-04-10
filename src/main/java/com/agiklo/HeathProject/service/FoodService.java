package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.mapper.FoodMapper;
import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.Food;
import com.agiklo.HeathProject.model.dto.FoodDTO;
import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import com.agiklo.HeathProject.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final FoodMapper foodMapper;

    @Transactional
    public List<FoodDTO> getAllFoods(Pageable pageable) {
        return foodRepository.findAll(pageable)
                .stream()
                .map(foodMapper::mapFoodToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FoodDTO getFoodById(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Food does not exist"));
        return foodMapper.mapFoodToDTO(food);
    }

    @Transactional
    public List<FoodDTO> getAllUserFoodsById(Long id) {
        return foodRepository.getAllByAuthor_Id(id).stream()
                .map(foodMapper::mapFoodToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Food addNewFood(Food food, Principal principal) {
        ApplicationUser user = applicationUserRepository.findByEmail(principal.getName()).orElseThrow(() ->
                new IllegalStateException("User not found"));
        food.setAuthor(user);
        food.setPostedAt(LocalDateTime.now());
        return foodRepository.save(food);
    }

    public void deleteFoodById(Long id, Principal principal) {
        Food food = foodRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Food does not exist"));
        if (principal.getName().equals(food.getAuthor().getEmail())){
            foodRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not owner of this food");
        }
    }
}

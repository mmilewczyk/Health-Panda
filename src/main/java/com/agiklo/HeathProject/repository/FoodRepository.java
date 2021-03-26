package com.agiklo.HeathProject.repository;

import com.agiklo.HeathProject.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> getAllByAuthor_Id(Long id);
}

package com.agiklo.HeathProject.mapper;

import com.agiklo.HeathProject.model.Food;
import com.agiklo.HeathProject.model.dto.FoodDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(target = "author", source = "author.email")
    FoodDTO mapFoodToDTO(Food food);
}

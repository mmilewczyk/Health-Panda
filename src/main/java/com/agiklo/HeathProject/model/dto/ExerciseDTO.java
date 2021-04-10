package com.agiklo.HeathProject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDTO {

    private String workout;
    private String date;
    private Long exerciseId;
    private String exerciseName;
    private List<SetDTO> set;

    public List<SetDTO> getSet() {
        return set;
    }

    public void setSet(List<SetDTO> set) {
        this.set = set;
    }

    public void addSet(SetDTO setDTO) {
        if (set == null) {
            set = new ArrayList<>();
        }
        set.add(setDTO);
    }
}

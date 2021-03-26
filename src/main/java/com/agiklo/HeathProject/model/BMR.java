package com.agiklo.HeathProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BMR {

    private Double bodyWeight;
    private Double height;
    private Integer age;
}

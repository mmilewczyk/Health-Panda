package com.agiklo.HeathProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BMR {

    private double bodyWeight;
    private double height;
    private int age;
}

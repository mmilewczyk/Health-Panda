package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.BMI;
import com.agiklo.HeathProject.model.BMR;
import org.springframework.stereotype.Service;

@Service
public class BMIService {

    public double bmiCalculator(BMI bmi){
        double bmiValue = bmi.getMass() / (bmi.getHeight() * bmi.getHeight());
        bmiValue *= 100;
        bmiValue = Math.round(bmiValue);
        bmiValue /= 100;
        return bmiValue;
    }
}

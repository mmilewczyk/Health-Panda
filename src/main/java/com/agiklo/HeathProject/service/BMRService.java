package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.BMR;
import org.springframework.stereotype.Service;

@Service
public class BMRService {

    public double calculate(BMR bmr){
         return 66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge());
    }
    //1,0 – leżący lub siedzący tryb życia, brak aktywności fizycznej
    public double bmrCalculator(BMR bmr){
        return calculate(bmr);
    }

    //1,2 – praca siedząca, aktywność fizyczna na niskim poziomie
    public double bmrCalculator12(BMR bmr){
        return 1.2 * calculate(bmr);
    }
    //1,4 – praca nie fizyczna, trening 2 razy w tygodniu
    public double bmrCalculator14(BMR bmr){
        return 1.4 * calculate(bmr);
    }
    //1,6 – lekka praca fizyczna, trening 3-4 razy w tygodniu
    public double bmrCalculator16(BMR bmr){
        return 1.6 * calculate(bmr);
    }
    //1,8 – praca fizyczna, trening 5 razy w tygodniu
    public double bmrCalculator18(BMR bmr){
        return 1.8 * calculate(bmr);
    }
    //2,0 – ciężka praca fizyczna, codzienny trening
    public double bmrCalculator20(BMR bmr){
        return 2.0 * calculate(bmr);
    }
}

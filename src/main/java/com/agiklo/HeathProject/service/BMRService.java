package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.BMR;
import org.springframework.stereotype.Service;

@Service
public class BMRService {

    //1,0 – leżący lub siedzący tryb życia, brak aktywności fizycznej
    public double bmrCalculator(BMR bmr){
        double bmrValue = 66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge());
        return bmrValue;
    }

    //1,2 – praca siedząca, aktywność fizyczna na niskim poziomie
    public double bmrCalculator12(BMR bmr){
        double bmrValue12 = 1.2 * (66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge()));
        return bmrValue12;
    }
    //1,4 – praca nie fizyczna, trening 2 razy w tygodniu
    public double bmrCalculator14(BMR bmr){
        double bmrValue14 = 1.4 * (66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge()));
        return bmrValue14;
    }
    //1,6 – lekka praca fizyczna, trening 3-4 razy w tygodniu
    public double bmrCalculator16(BMR bmr){
        double bmrValue16 = 1.6 * (66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge()));
        return bmrValue16;
    }
    //1,8 – praca fizyczna, trening 5 razy w tygodniu
    public double bmrCalculator18(BMR bmr){
        double bmrValue18 = 1.8 * (66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge()));
        return bmrValue18;
    }
    //2,0 – ciężka praca fizyczna, codzienny trening
    public double bmrCalculator20(BMR bmr){
        double bmrValue20 = 2.0 * (66 + (13.7 * bmr.getBodyWeight()) + (5 * bmr.getHeight()) - (6.76 * bmr.getAge()));
        return bmrValue20;
    }
}

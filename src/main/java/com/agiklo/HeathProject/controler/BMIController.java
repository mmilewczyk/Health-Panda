package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.BMI;
import com.agiklo.HeathProject.model.BMR;
import com.agiklo.HeathProject.service.BMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BMIController {

    private BMIService bmiService;

    @Autowired
    public BMIController(BMIService bmiService) {
        this.bmiService = bmiService;
    }

    @RequestMapping("/bmi")
    public String getBmrPage(BMI bmi, Model model) {
        model.addAttribute("bmi", bmi);
        return "bmi";
    }

    @RequestMapping(value="/bmi", params="bmiCalculator", method = RequestMethod.POST)
    public String bmrCalculator(@ModelAttribute("bmi") BMI bmi, Model model ){
        model.addAttribute("result", bmiService.bmiCalculator(bmi));
        return "bmi";
    }
}

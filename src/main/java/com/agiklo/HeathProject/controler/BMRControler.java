package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.BMR;
import com.agiklo.HeathProject.service.BMRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BMRControler {

    private BMRService bmrService;

    @Autowired
    public BMRControler(BMRService bmrService) {
        this.bmrService = bmrService;
    }

    @RequestMapping("/bmr")
    public String getBmrPage(BMR bmr, Model model){
        model.addAttribute("bmr", bmr);
        return "bmr";
    }

    @RequestMapping(value="/bmr", params="bmrCalculator", method = RequestMethod.POST)
    public String bmrCalculator(@ModelAttribute("bmr") BMR bmr, Model model ){
        model.addAttribute("result", bmrService.bmrCalculator(bmr));
        return "bmr";
    }
    @RequestMapping(value="/bmr", params="bmrCalculator12", method = RequestMethod.POST)
    public String bmrCalculator12(@ModelAttribute("bmr") BMR bmr, Model model ){
        model.addAttribute("result", bmrService.bmrCalculator12(bmr));
        return "bmr";
    }
    @RequestMapping(value="/bmr", params="bmrCalculator14", method = RequestMethod.POST)
    public String bmrCalculator14(@ModelAttribute("bmr") BMR bmr, Model model ){
        model.addAttribute("result", bmrService.bmrCalculator14(bmr));
        return "bmr";
    }
    @RequestMapping(value="/bmr", params="bmrCalculator16", method = RequestMethod.POST)
    public String bmrCalculator16(@ModelAttribute("bmr") BMR bmr, Model model ){
        model.addAttribute("result", bmrService.bmrCalculator16(bmr));
        return "bmr";
    }
    @RequestMapping(value="/bmr", params="bmrCalculator18", method = RequestMethod.POST)
    public String bmrCalculator18(@ModelAttribute("bmr") BMR bmr, Model model ){
        model.addAttribute("result", bmrService.bmrCalculator18(bmr));
        return "bmr";
    }
    @RequestMapping(value="/bmr", params="bmrCalculator20", method = RequestMethod.POST)
    public String bmrCalculator20(@ModelAttribute("bmr") BMR bmr, Model model ){
        model.addAttribute("result", bmrService.bmrCalculator20(bmr));
        return "bmr";
    }
}

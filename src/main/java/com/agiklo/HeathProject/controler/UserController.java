package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.BMR;
import com.agiklo.HeathProject.model.User;
import com.agiklo.HeathProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String getLoginPage(User user, Model model){
        model.addAttribute("login", user);
        return "login";
    }
    @RequestMapping("/register")
    public String getRegisterPage(User user, Model model){
        model.addAttribute("register", user);
        return "register";
    }

    @RequestMapping(value="/register", params="newUser", method = RequestMethod.POST)
    public String newUser(@ModelAttribute("user") User user, Model model ) {
        model.addAttribute("result", userService.createAccount(user));
        return "user";
    }
}

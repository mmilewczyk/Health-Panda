package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.User;
import com.agiklo.HeathProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/registration")
    public String getRegistrationPage(User user, Model model){
        model.addAttribute("registration", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addNewUser(User user, Model model){
        model.addAttribute("result", userRepository.saveAndFlush(user));
        return "redirect:/login";
    }

}
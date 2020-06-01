package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    private UserDetailsServiceImpl userService;

    @Autowired
    public LoginController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

}

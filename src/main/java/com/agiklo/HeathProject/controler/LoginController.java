package com.agiklo.HeathProject.controler;

import com.agiklo.HeathProject.model.User;
import com.agiklo.HeathProject.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        long currentTimeMillis =System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles", user.getRole())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 20000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }

}

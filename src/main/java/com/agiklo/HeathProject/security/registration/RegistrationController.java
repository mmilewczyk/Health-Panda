package com.agiklo.HeathProject.security.registration;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import static com.agiklo.HeathProject.controler.ApiMapping.REGISTRATION_REST_URL;

@RestController
@RequestMapping(REGISTRATION_REST_URL)
@AllArgsConstructor
@EnableWebSecurity
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}

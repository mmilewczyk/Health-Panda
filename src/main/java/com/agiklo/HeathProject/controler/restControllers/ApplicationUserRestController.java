package com.agiklo.HeathProject.controler.restControllers;

import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.agiklo.HeathProject.controler.ApiMapping.USERS_REST_URL;

@RestController
@AllArgsConstructor
@RequestMapping(USERS_REST_URL)
public class ApplicationUserRestController {

    private final ApplicationUserRepository applicationUserRepository;

    @GetMapping("/api/v2/users")
    public List<ApplicationUser> getAllUsers(){
        return applicationUserRepository.findAll();
    }
}

package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.User;
import com.agiklo.HeathProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createAccount(User user){
        return userRepository.save(user);
    }
}

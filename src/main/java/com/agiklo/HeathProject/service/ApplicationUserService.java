package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    private static final String USER_NOT_FOUND_MSG =
            "user with email %s not found";


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return applicationUserRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
}

package com.agiklo.HeathProject.service;

import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.repository.ApplicationUserRepository;
import com.agiklo.HeathProject.security.registration.token.ConfirmationToken;
import com.agiklo.HeathProject.security.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

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

    public String signUpUser(ApplicationUser applicationUser){
        boolean userExists = applicationUserRepository.findByEmail(applicationUser.getEmail()).isPresent();
        if (userExists){
            //TODO: IF USER NOT CONFIRMED, SEND EMAIL AGAIN
            throw new IllegalStateException(
                    String.format("Email %s already taken", applicationUser.getEmail()));
        }

        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        applicationUserRepository.save(applicationUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                applicationUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return applicationUserRepository.enableUser(email);
    }
}

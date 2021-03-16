package com.agiklo.HeathProject.security.registration;

import com.agiklo.HeathProject.model.ApplicationUser;
import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.security.email.EmailBuilder;
import com.agiklo.HeathProject.security.email.EmailSender;
import com.agiklo.HeathProject.security.registration.token.ConfirmationToken;
import com.agiklo.HeathProject.security.registration.token.ConfirmationTokenService;
import com.agiklo.HeathProject.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ApplicationUserService applicationUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException(
                    String.format("Email %s is not valid!", request.getEmail()));
        }

        String token = applicationUserService.signUpUser(
                new ApplicationUser(
                        request.getEmail(),
                        request.getPassword(),
                        USER_ROLE.USER
                ));

        String link = "http://localhost:8080/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(),
                EmailBuilder.buildEmail(request.getEmail(),
                        link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        applicationUserService.enableUser(
                confirmationToken.getApplicationUser().getEmail());
        return "confirmed";
    }
}

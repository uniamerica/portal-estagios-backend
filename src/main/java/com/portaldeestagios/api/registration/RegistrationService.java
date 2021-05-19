package com.portaldeestagios.api.registration;

import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRole;
import com.portaldeestagios.api.user.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
  private final ApplicationUserService applicationUserService;
//  private final EmailValidator emailValidator;
//  private final ConfirmationTokenService confirmationTokenService;
//  private final EmailSender emailSender;


  public String register(RegistrationDTO request,String role) {
//    boolean isValidEmail = emailValidator
//            .test(request.getEmail());
//
//    if(!isValidEmail) {
//      throw new IllegalStateException("email not valid");
//    }
    String token = applicationUserService.signUpUser(
            new ApplicationUser(
                    request.getEmail(),
                    request.getPassword(),
                    ApplicationUserRole.valueOf(role.toUpperCase())
            )
    );
    String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
//    emailSender.send(request.getEmail(), buildEmail(request.getFirstName(), link));
    return token;
  }

//  @Transactional
//  public String confirmToken(String token) {
//    ConfirmationToken confirmationToken = confirmationTokenService
//            .getToken(token)
//            .orElseThrow(() ->
//                    new IllegalStateException("token not found"));
//
//    if (confirmationToken.getConfirmedAt() != null) {
//      throw new IllegalStateException("email already confirmed");
//    }
//
//    LocalDateTime expiredAt = confirmationToken.getExpiresAt();
//
//    if (expiredAt.isBefore(LocalDateTime.now())) {
//      throw new IllegalStateException("token expired");
//    }
//
//    confirmationTokenService.setConfirmedAt(token);
//    appUserService.enableAppUser(
//            confirmationToken.getAppUser().getEmail());
//    return "confirmed";
//  }
}

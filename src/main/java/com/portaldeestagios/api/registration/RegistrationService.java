package com.portaldeestagios.api.registration;

import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRole;
import com.portaldeestagios.api.user.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
  private final ApplicationUserService applicationUserService;


  public ApplicationUser register(RegistrationRequest request, String role) {
    return applicationUserService.signUpUser(
            new ApplicationUser(
                    request.getEmail(),
                    request.getPassword(),
                    ApplicationUserRole.valueOf(role.toUpperCase())
            )
    );
  }
}

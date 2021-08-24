package com.portaldeestagios.api.registration;

import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRole;
import com.portaldeestagios.api.user.ApplicationUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {


  @InjectMocks
  private RegistrationService registrationService;

  @Mock
  private ApplicationUserService applicationUserService;

  @Test
  void deveCriarUmUsuarioParaRegistrar() {

    var request = new RegistrationRequest();

    request.setPassword("12345");
    request.setEmail("teste@teste.com");

    var role = "ROLE_student";

    var user = new ApplicationUser(
            request.getEmail(),
            request.getPassword(),
            ApplicationUserRole.valueOf(role.toUpperCase()
            ));

    doNothing().when(applicationUserService).signUpUser(user);

    registrationService.register(request, role);


    assertEquals("ROLE_STUDENT", user.getApplicationUserRole().toString());
    verify(applicationUserService, times(1)).signUpUser(user);
  }
}
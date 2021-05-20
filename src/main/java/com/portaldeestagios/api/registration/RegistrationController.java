package com.portaldeestagios.api.registration;

import com.portaldeestagios.api.user.ApplicationUserRole;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/registration/")
@AllArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/{role}")
  public String register(@RequestBody RegistrationRequest request, @PathVariable String role) {
    role = "ROLE_"+role;
    if(!EnumUtils.isValidEnumIgnoreCase(ApplicationUserRole.class, role) ||
            role.equalsIgnoreCase("role_admin")) {
      throw new IllegalStateException("Invalid path");
    }
    registrationService.register(request, role);

    return "Cadastro efetuado com sucesso";
  }

//  @GetMapping(path = "confirm")
//  public String confirm(@RequestParam("token") String token) {
//    return registrationService.confirmToken(token);
//  }
}

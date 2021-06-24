package com.portaldeestagios.api.registration;

import com.portaldeestagios.api.user.ApplicationUserRole;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/registration/")
@AllArgsConstructor
@Api(tags = "Registration")
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

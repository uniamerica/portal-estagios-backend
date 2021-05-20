package com.portaldeestagios.api.registration;

import com.portaldeestagios.api.user.ApplicationUserRole;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/registration/")
@AllArgsConstructor
public class RegistrationController {

  private final RegistrationService registrationService;

  @PostMapping("/{role}")
  public String register(@RequestBody RegistrationRequest request, @PathVariable String role) {
    if(!EnumUtils.isValidEnumIgnoreCase(ApplicationUserRole.class, role) ||
            role.equalsIgnoreCase("admin")) {
      throw new IllegalStateException("Invalid path");
    }

    return registrationService.register(request, role);
  }

//  @GetMapping(path = "confirm")
//  public String confirm(@RequestParam("token") String token) {
//    return registrationService.confirmToken(token);
//  }
}

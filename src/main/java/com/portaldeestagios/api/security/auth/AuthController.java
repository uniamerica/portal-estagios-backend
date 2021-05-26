package com.portaldeestagios.api.security.auth;

import com.portaldeestagios.api.security.jwt.UsernamePasswordAuthenticationRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Authentication")
public class AuthController {
  @PostMapping("/login")
  public Void login(
          @RequestBody UsernamePasswordAuthenticationRequest usernamePasswordAuthenticationRequest) {
    return null;
  }
}

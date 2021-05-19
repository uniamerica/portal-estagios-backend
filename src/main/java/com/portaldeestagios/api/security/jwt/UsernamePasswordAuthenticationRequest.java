package com.portaldeestagios.api.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernamePasswordAuthenticationRequest {
  private String username;
  private String password;
}

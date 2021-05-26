package com.portaldeestagios.api.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken  {
  private String token;
}

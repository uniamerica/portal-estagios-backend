package com.portaldeestagios.api.registration;

import lombok.*;

@AllArgsConstructor
@Data
public class RegistrationRequest {
  private final String password;
  private final String email;
}

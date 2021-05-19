package com.portaldeestagios.api.registration;

import lombok.*;

@AllArgsConstructor
@Data
public class RegistrationDTO {
  private final String password;
  private final String email;
}

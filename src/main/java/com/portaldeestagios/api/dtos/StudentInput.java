package com.portaldeestagios.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInput {

  private String firstName;
  private String lastName;
  private Byte age;

  private UserIdInput applicationUser;
}

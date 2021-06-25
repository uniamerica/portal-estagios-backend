package com.portaldeestagios.api.dtos.model.student;

import com.portaldeestagios.api.dtos.model.user.UserListDto;

public class StudentDto {
  private String firstName;
  private String lastName;
  private Byte age;

  private UserListDto applicationUser;
}

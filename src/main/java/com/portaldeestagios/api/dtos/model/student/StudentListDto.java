package com.portaldeestagios.api.dtos.model.student;

import com.portaldeestagios.api.dtos.model.user.UserListDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentListDto {
  private String firstName;
  private String lastName;
  private Byte age;

  private UserListDto applicationUser;
}

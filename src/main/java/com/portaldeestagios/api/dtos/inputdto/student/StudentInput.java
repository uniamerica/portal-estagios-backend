package com.portaldeestagios.api.dtos.inputdto.student;

import lombok.*;

@Getter
@Setter
@Builder
public class StudentInput {

  private String firstName;
  private String lastName;
  private Byte age;
  private String photo;

}

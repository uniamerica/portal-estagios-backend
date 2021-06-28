package com.portaldeestagios.api.dtos.model.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
  private Long id;
  private String firstName;
  private String lastName;
  private Byte age;
  private String photo;

}

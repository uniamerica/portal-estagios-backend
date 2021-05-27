package com.portaldeestagios.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentModel {
  private String firstName;
  private String lastName;
  private Byte age;

  private UserResumoModel applicationUser;
  private List<SelectionProcessDto> selectionProcessList;
}

package com.portaldeestagios.api.dtos.model.student;

import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentDto {
  private Long id;
  private String firstName;
  private String lastName;
  private Byte age;
  private String photo;

  private Set<SelectionProcessEntity> selectionProcessEntityList = new HashSet<>();
}

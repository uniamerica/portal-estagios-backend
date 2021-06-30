package com.portaldeestagios.api.dtos.model.student;

import com.portaldeestagios.api.dtos.model.selectionprocess.SelectionProcessListDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentProfileDto {
  private Long id;
  private String firstName;
  private String lastName;
  private Byte age;
  private String photo;

  private List<SelectionProcessListDto> selectionProcess;
}

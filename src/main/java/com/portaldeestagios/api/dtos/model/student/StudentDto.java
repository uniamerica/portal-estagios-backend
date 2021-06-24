package com.portaldeestagios.api.dtos.model.student;

import com.portaldeestagios.api.dtos.model.user.UserListDto;
import com.portaldeestagios.api.dtos.model.selectionprocess.SelectionProcessDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDto {
  private String firstName;
  private String lastName;
  private Byte age;

  private UserListDto applicationUser;
  private List<SelectionProcessDto> selectionProcessList;
}

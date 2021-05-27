package com.portaldeestagios.api.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SelectionProcessDto implements Serializable {
  private String id;
  private String title;

  private List<StudentInput> studentList;
}

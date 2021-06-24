package com.portaldeestagios.api.dtos.assembler.student;

import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.student.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public Student toEntity(StudentInput student) {
    return modelMapper.map(student, Student.class);
  }
}

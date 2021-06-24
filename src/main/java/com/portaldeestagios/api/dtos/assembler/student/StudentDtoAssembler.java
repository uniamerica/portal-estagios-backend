package com.portaldeestagios.api.dtos.assembler.student;

import com.portaldeestagios.api.dtos.model.student.StudentDto;
import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import com.portaldeestagios.api.student.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentDtoAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public StudentDto toModel(Student student) {
    return modelMapper.map(student, StudentDto.class);
  }

  public StudentListDto toModelList(Student student) {
    return modelMapper.map(student, StudentListDto.class);
  }

  public List<StudentListDto> toCollectionModel(List<Student> students){
    return students.stream()
            .map(this::toModelList)
            .collect(Collectors.toList());
  }
}

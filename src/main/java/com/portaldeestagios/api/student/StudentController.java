package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.StudentInput;
import com.portaldeestagios.api.dtos.StudentModel;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students/")
@AllArgsConstructor
@Api(tags = "Student")
public class StudentController {

  private final StudentService service;
  private final StudentRepository repository;
  private final ModelMapper modelMapper;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<StudentModel>> findAll() {
    List<StudentModel> list = toCollectionModel(repository.findAll());
    return ResponseEntity.ok(list);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public StudentModel save(@RequestBody StudentInput studentInput){
    Student student = toEntity(studentInput);
    return toModel(service.save(student));
  }

  private StudentModel toModel(Student student){
    return modelMapper.map(student, StudentModel.class);
  }

  private List<StudentModel> toCollectionModel(List<Student> student) {
    return student.stream().map(this::toModel).collect(Collectors.toList());
  }

  private Student toEntity(StudentInput studentInput){
    return modelMapper.map(studentInput, Student.class);
  }
}

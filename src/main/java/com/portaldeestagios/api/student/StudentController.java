package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.SelectionProcessDto;
import com.portaldeestagios.api.dtos.StudentInput;
import com.portaldeestagios.api.dtos.StudentModel;
import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @ApiOperation("Find all students")
  public ResponseEntity<List<StudentModel>> findAll() {
    List<StudentModel> list = toCollectionModel(repository.findAll());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{studentId}")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @ApiOperation("Find a student by Id")
  public ResponseEntity<StudentModel> findById(@ApiParam(value = "Student Id", example = "1") @PathVariable Long studentId) {

    StudentModel student = toModel(repository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student not found...")));
    return ResponseEntity.ok(student);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @ApiOperation("Save a student")
  public StudentModel save(@RequestBody StudentInput studentInput){
    Student student = toEntity(studentInput);
    return toModel(service.save(student));
  }

  private List<SelectionProcessDto> toCollectionModel(List<StudentModel> selectionProcessList) {
    return selectionProcessList.stream().map(this::toModel).collect(Collectors.toList());
  }
  private StudentModel toModel(StudentModel studentModel){
    return modelMapper.map(studentModel, StudentModel.class);
  }

}

package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import com.portaldeestagios.api.dtos.assembler.student.StudentDtoAssembler;
import com.portaldeestagios.api.dtos.assembler.student.StudentDtoDisassembler;
import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.dtos.model.student.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students/")
@AllArgsConstructor
public class StudentController  implements StudentControllerOpenApi{

  private final StudentService service;
  private final StudentRepository repository;
  private final StudentDtoAssembler studentDtoAssembler;
  private final StudentDtoDisassembler studentDtoDisassembler;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Transactional
  public ResponseEntity<List<StudentListDto>> findAll(){
    List<StudentListDto> list = studentDtoAssembler.toCollectionModel(repository.findAll());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{studentId}")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public ResponseEntity<StudentDto> findById(@PathVariable Long studentId) {

    StudentDto student = studentDtoAssembler.toModel(repository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student not found...")));
    return ResponseEntity.ok(student);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public StudentDto save(@RequestBody StudentInput studentInput, Authentication authentication) {
    String email = authentication.getName();

    Student student = studentDtoDisassembler.toEntity(studentInput);
    return studentDtoAssembler.toModel(service.save(student, email));
  }
}

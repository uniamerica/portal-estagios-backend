package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.assembler.student.StudentProfileDtoAssembler;
import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import com.portaldeestagios.api.dtos.assembler.student.StudentDtoAssembler;
import com.portaldeestagios.api.dtos.assembler.student.StudentDtoDisassembler;
import com.portaldeestagios.api.dtos.inputdto.student.StudentInput;
import com.portaldeestagios.api.dtos.model.student.StudentProfileDto;
import com.portaldeestagios.api.dtos.model.student.StudentTokenDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/")
@AllArgsConstructor
public class StudentController  implements StudentControllerOpenApi{

  private final StudentService service;
  private final StudentRepository repository;
  private final StudentDtoAssembler studentDtoAssembler;
  private final StudentProfileDtoAssembler studentProfileDtoAssembler;
  private final StudentDtoDisassembler studentDtoDisassembler;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Transactional
  public ResponseEntity<List<StudentListDto>> findAll(){
    List<StudentListDto> list = studentDtoAssembler.toCollectionModel(repository.findAll());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/token")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @Transactional
  public ResponseEntity<StudentTokenDto> findByToken(Authentication authentication) {
    String email = authentication.getName();
    StudentTokenDto student = studentDtoAssembler.toModel(service.findByEmail(email));
    return ResponseEntity.ok(student);
  }

  @GetMapping("/profile")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @Transactional
  public ResponseEntity<StudentProfileDto> findByTokenProfile(Authentication authentication) {
    String email = authentication.getName();
    StudentProfileDto student = studentProfileDtoAssembler.toModel(service.findByEmail(email));
    return ResponseEntity.ok(student);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public StudentTokenDto save(@RequestBody StudentInput studentInput, Authentication authentication) {
    String email = authentication.getName();

    return studentDtoAssembler.toModel(service.update(studentInput, email));
  }
}

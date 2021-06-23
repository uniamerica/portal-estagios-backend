package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.model.student.StudentDto;
import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Students")
public interface StudentControllerOpenApi {
  @ApiOperation("Find all students")
  ResponseEntity<List<StudentListDto>> findAll();

  @ApiOperation("Find a student by Id")
  ResponseEntity<StudentDto> findById(@ApiParam(value = "Student Id", example = "1") @PathVariable Long studentId);

  @ApiOperation("Save a student")
  StudentDto save(@RequestBody StudentInput studentInput, Authentication authentication) throws NoSuchFieldException;
}

package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.inputDto.student.StudentInput;
import com.portaldeestagios.api.dtos.model.student.StudentDto;
import com.portaldeestagios.api.dtos.model.student.StudentProfileDto;
import com.portaldeestagios.api.dtos.model.student.StudentTokenDto;
import com.portaldeestagios.api.dtos.model.student.StudentListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Students")
public interface StudentControllerOpenApi {
  @ApiOperation("Find all students")
  ResponseEntity<List<StudentListDto>> findAll();

  @ApiOperation("Find a student by Token")
  ResponseEntity<StudentProfileDto> findByTokenProfile(@ApiParam(value = "Student Id", example = "1") Authentication authentication);

  @ApiOperation("Save a student")
  StudentTokenDto save(@RequestBody StudentInput studentInput, Authentication authentication) throws NoSuchFieldException;
}

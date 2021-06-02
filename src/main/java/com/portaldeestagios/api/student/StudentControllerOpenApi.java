package com.portaldeestagios.api.student;

import com.portaldeestagios.api.dtos.StudentModel;
import com.portaldeestagios.api.dtos.inputDto.StudentInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Students")
public interface StudentControllerOpenApi {
  @ApiOperation("Find all students")
  ResponseEntity<List<StudentModel>> findAll();

  @ApiOperation("Find a student by Id")
  ResponseEntity<StudentModel> findById(@ApiParam(value = "Student Id", example = "1") @PathVariable Long studentId);

  @ApiOperation("Save a student")
  StudentModel save(@RequestBody StudentInput studentInput);
}

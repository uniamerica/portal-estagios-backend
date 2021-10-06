package com.portaldeestagios.api.customhandlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
  private String timestamp;
  private int status;
  private String message;
  private String path;


  static void customExceptionHandling(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setStatus(HttpStatus.FORBIDDEN.value());

    ResponseModel data = new ResponseModel(
            OffsetDateTime.now().toString(),
            HttpStatus.FORBIDDEN.value(),
            "Access Denied",
            request.getRequestURL().toString());


    OutputStream out = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, data);
    out.flush();
  }

  static void unauthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    ResponseModel data = new ResponseModel(
            OffsetDateTime.now().toString(),
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            request.getRequestURL().toString());


    OutputStream out = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, data);
    out.flush();
  }
}

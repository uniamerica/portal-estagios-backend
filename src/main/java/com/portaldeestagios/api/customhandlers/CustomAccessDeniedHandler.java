package com.portaldeestagios.api.customhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  public CustomAccessDeniedHandler() {
  }


  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException {

    response.setStatus(HttpStatus.NOT_FOUND.value());
    response.getWriter().print("You don't have required role to perform this action.");
    response.sendRedirect("http://localhost:8000/login");
  }
}




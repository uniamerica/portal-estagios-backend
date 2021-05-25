package com.portaldeestagios.api.customhandlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomHTTP403 implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest httpServletRequest,
                       HttpServletResponse response,
                       AuthenticationException e) throws IOException, ServletException {

    response.setStatus(403);
  }
}

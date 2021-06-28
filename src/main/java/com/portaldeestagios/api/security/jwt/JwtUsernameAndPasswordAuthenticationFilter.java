package com.portaldeestagios.api.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaldeestagios.api.customhandlers.ResponseModel;
import com.portaldeestagios.api.user.ApplicationUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.OffsetDateTime;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtConfig jwtConfig;
  private final JwtUtils jwtUtils;


  public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtConfig = jwtConfig;
    this.jwtUtils = jwtUtils;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      UsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
            .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);

      Authentication authentication = new UsernamePasswordAuthenticationToken(
              authenticationRequest.getUsername(),
              authenticationRequest.getPassword()
      );
      return authenticationManager.authenticate(authentication);

    } catch (IOException e){
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) {

    ApplicationUser user = (ApplicationUser) authResult.getPrincipal();

    String token = jwtUtils.createToken(user);

    response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    ResponseModel data = new ResponseModel(
            OffsetDateTime.now().toString(),
            HttpStatus.UNAUTHORIZED.value(),
            "Bad Credentials",
            req.getRequestURL().toString());

    OutputStream out = res.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, data);
    out.flush();
  }
}

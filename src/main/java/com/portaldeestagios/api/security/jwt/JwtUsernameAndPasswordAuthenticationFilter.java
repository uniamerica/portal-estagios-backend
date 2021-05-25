package com.portaldeestagios.api.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaldeestagios.api.user.ApplicationUser;
import io.jsonwebtoken.Jwts;
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
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;


  public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
    this.authenticationManager = authenticationManager;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    try {
      UsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
            .readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);
/*
      String username = this.obtainUsername(request);
      String password = this.obtainPassword(request);
*/

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
                                          Authentication authResult) throws IOException, ServletException {

    ApplicationUser user = (ApplicationUser) authResult.getPrincipal();


    createToken(response, authResult, user);


//    Cookie cookie = new Cookie("token", token);
//    cookie.setPath("/");
//    cookie.setHttpOnly(true);
//    cookie.setMaxAge(60 * 30); // 30 minutos
//    response.addCookie(cookie);


  }

  private void createToken(HttpServletResponse response, Authentication authResult, ApplicationUser user) {
    String token = Jwts.builder()
            .setSubject(authResult.getName())
            .claim("authorities", authResult.getAuthorities())
            .claim("id", user.getId())
            .setIssuedAt(new Date())
            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
            .signWith(secretKey)
            .compact();

    response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    res.setContentType("application/json");
    res.setCharacterEncoding("utf-8");
  }
}

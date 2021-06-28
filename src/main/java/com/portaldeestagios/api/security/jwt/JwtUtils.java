package com.portaldeestagios.api.security.jwt;

import com.portaldeestagios.api.dtos.assembler.student.StudentDtoAssembler;
import com.portaldeestagios.api.dtos.model.student.StudentTokenDto;
import com.portaldeestagios.api.student.Student;
import com.portaldeestagios.api.student.StudentService;
import com.portaldeestagios.api.user.ApplicationUser;
import com.portaldeestagios.api.user.ApplicationUserRole;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

import static com.portaldeestagios.api.user.ApplicationUserRole.*;

@Component
@AllArgsConstructor
public class JwtUtils {
  private final SecretKey secretKey;
  private final StudentService studentService;
  private final StudentDtoAssembler studentDtoAssembler;

  public String createToken(ApplicationUser user) {

    StudentTokenDto entity = null;

    if(user.getApplicationUserRole().equals(ROLE_STUDENT)) {
      entity = studentDtoAssembler.toModel(studentService.findByEmail(user.getEmail()));
    }

    return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("authorities", user.getAuthorities())
            .claim("entity", entity)
            .setId(user.getId().toString())
            .setIssuedAt(new Date())
            .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
            .signWith(secretKey)
            .compact();
  }
}

package com.portaldeestagios.api.dtos.inputdto.student;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
public class StudentInput {

  private String firstName;
  private String lastName;
  private Byte age;
  private String photo;

  @CPF
  private String cpf;

}

package com.portaldeestagios.api.security.jwt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernamePasswordAuthenticationRequest {

  @ApiModelProperty(example = "user@gmail.com", required = true)
  private String username;

  @ApiModelProperty(example = "password", required = true)
  private String password;




}

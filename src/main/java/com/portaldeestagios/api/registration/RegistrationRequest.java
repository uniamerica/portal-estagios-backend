package com.portaldeestagios.api.registration;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
public class RegistrationRequest {

  @ApiModelProperty(example = "user@gmail.com", required = true)
  private String email;

  @ApiModelProperty(example = "password", required = true, position = 1)
  private String password;

}

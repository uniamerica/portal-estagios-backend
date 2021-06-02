package com.portaldeestagios.api.registration;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@Data
public class RegistrationRequest {

  @ApiModelProperty(example = "user@gmail.com", required = true)
  private final String email;

  @ApiModelProperty(example = "password", required = true, position = 1)
  private final String password;

}

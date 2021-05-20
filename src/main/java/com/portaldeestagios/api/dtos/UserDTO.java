package com.portaldeestagios.api.dtos;

import com.portaldeestagios.api.user.ApplicationUserRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO  {

  private String email;
  private ApplicationUserRole role;

}

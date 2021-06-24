package com.portaldeestagios.api.dtos.model.user;

import com.portaldeestagios.api.user.ApplicationUserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListDto {
  private Long id;
  private String email;
  private ApplicationUserRole applicationUserRole;
}

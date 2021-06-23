package com.portaldeestagios.api.dtos.model.user;

import com.portaldeestagios.api.user.ApplicationUserRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {
  private Long id;
  private String email;
  private ApplicationUserRole applicationUserRole;
  private boolean isAccountNonExpired;
  private boolean isAccountNonLocked;
  private boolean isCredentialsNonExpired;
  private boolean isEnabled;
}

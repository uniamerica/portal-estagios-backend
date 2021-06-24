package com.portaldeestagios.api.dtos.assembler.user;

import com.portaldeestagios.api.registration.RegistrationRequest;
import com.portaldeestagios.api.user.ApplicationUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public ApplicationUser toEntity(RegistrationRequest student) {
    return modelMapper.map(student, ApplicationUser.class);
  }
}

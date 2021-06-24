package com.portaldeestagios.api.dtos.assembler.user;

import com.portaldeestagios.api.dtos.model.user.UserDto;
import com.portaldeestagios.api.dtos.model.user.UserListDto;
import com.portaldeestagios.api.user.ApplicationUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public UserDto toModel(ApplicationUser user) {
    return modelMapper.map(user, UserDto.class);
  }

  public UserListDto toModelList(ApplicationUser user) {
    return modelMapper.map(user, UserListDto.class);
  }

  public List<UserListDto> toCollectionModel(List<ApplicationUser> users){
    return users.stream()
            .map(this::toModelList)
            .collect(Collectors.toList());
  }
}

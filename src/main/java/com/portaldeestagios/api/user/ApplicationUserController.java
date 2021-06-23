package com.portaldeestagios.api.user;

import com.portaldeestagios.api.dtos.assembler.user.UserDtoAssembler;
import com.portaldeestagios.api.dtos.model.user.UserDto;
import com.portaldeestagios.api.dtos.model.user.UserListDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application-user/")
@AllArgsConstructor
@Api(tags = "Users")
public class ApplicationUserController {

  private final ApplicationUserRepository applicationUserRepository;
  private final UserDtoAssembler userDtoAssembler;
  private final ApplicationUserService applicationUserService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<UserListDto> listUser() {
    return userDtoAssembler.toCollectionModel(applicationUserRepository.findAll());
  }

  @GetMapping("/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserDto getUser(@PathVariable Long userId) {
    return userDtoAssembler.toModel(applicationUserService.findOrFail(userId));
  }

  @DeleteMapping("/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    applicationUserRepository.deleteById(userId);
    return ResponseEntity.noContent().build();
  }

}

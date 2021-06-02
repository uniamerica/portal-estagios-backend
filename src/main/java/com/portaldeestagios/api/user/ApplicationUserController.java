package com.portaldeestagios.api.user;

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

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<ApplicationUser> listUser() {
    return applicationUserRepository.findAll();
  }

  @GetMapping("/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ApplicationUser getUser(@PathVariable Long userId) {
    return applicationUserRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
  }

  @DeleteMapping("/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    applicationUserRepository.deleteById(userId);
    return ResponseEntity.noContent().build();
  }

}

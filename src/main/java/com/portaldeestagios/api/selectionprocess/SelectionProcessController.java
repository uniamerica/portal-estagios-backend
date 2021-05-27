package com.portaldeestagios.api.selectionprocess;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/selection-process")
public class SelectionProcessController {

  private final SelectionProcessRepository repository;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public ResponseEntity<List<SelectionProcessEntity>> findAll() {
    List<SelectionProcessEntity> list = repository.findAll();
    return ResponseEntity.ok(list);
  }
}

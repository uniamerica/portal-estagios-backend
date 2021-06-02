package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.dtos.inputDto.InputSelectionProcess;
import com.portaldeestagios.api.dtos.SelectionProcessDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/selection-process")
@Api(tags = "Selection Process")
public class SelectionProcessController {

  private final SelectionProcessRepository repository;
  private final SelectionProcessService service;
  private final ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<List<SelectionProcessDto>> findAll() {
    List<SelectionProcessDto> list = toCollectionModel(repository.findAll());
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{selectionProcessId}")
  public ResponseEntity<SelectionProcessDto> findById(@PathVariable Long selectionProcessId) {
    SelectionProcessDto selectionProcess = toModel(repository.findById(selectionProcessId).orElseThrow(() -> new IllegalStateException("Selection Process Not Found")));
    return ResponseEntity.ok(selectionProcess);
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public SelectionProcessDto create(@RequestBody InputSelectionProcess selectionProcess) {
    SelectionProcessEntity selectionProcessEntity = toEntity(selectionProcess);
    return toModel(repository.save(selectionProcessEntity));
  }

  @PutMapping("/{selectionProcessId}/register")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public SelectionProcessDto register(@PathVariable Long selectionProcessId, Authentication authentication) {
    String email = authentication.getName();
    return toModel(service.register(selectionProcessId, email));
  }

  @PutMapping("/{selectionProcessId}/leave")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public SelectionProcessDto leave(@PathVariable Long selectionProcessId, Authentication authentication) {
    String email = authentication.getName();
    return toModel(service.leave(selectionProcessId, email));
  }

  @PatchMapping("/{selectionProcessId}/open")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public SelectionProcessDto open(@PathVariable Long selectionProcessId) {
    return toModel(service.open(selectionProcessId));
  }




  private List<SelectionProcessDto> toCollectionModel(List<SelectionProcessEntity> selectionProcessList) {
    return selectionProcessList.stream().map(this::toModel).collect(Collectors.toList());
  }

  private SelectionProcessDto toModel(SelectionProcessEntity selectionProcess) {
    return modelMapper.map(selectionProcess, SelectionProcessDto.class);
  }

  private SelectionProcessEntity toEntity(InputSelectionProcess selectionProcess) {
    return modelMapper.map(selectionProcess, SelectionProcessEntity.class);
  }
}



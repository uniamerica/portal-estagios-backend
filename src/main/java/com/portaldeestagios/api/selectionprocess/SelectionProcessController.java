package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.dtos.model.selectionprocess.SelectionProcessListDto;
import com.portaldeestagios.api.dtos.assembler.selectionProcess.SelectionProcessDtoAssembler;
import com.portaldeestagios.api.dtos.assembler.selectionProcess.SelectionProcessDtoDisassembler;
import com.portaldeestagios.api.dtos.inputDto.selectionprocess.InputSelectionProcess;
import com.portaldeestagios.api.dtos.model.selectionprocess.SelectionProcessDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/selection-process")
@Api(tags = "Selection Process")
public class SelectionProcessController {

  private final SelectionProcessDtoAssembler selectionProcessDtoAssembler;
  private final SelectionProcessDtoDisassembler selectionProcessDtoDisassembler;
  private final SelectionProcessRepository repository;
  private final SelectionProcessService selectionProcessService;
  private final SelectionProcessFlowService selectionProcessFlowService;

//  @Transactional
//  @GetMapping
//  public ResponseEntity<List<SelectionProcessListDto>> findAll() {
//    List<SelectionProcessListDto> list = selectionProcessDtoAssembler.toCollectionModel(repository.findAll());
//    return ResponseEntity.ok(list);
//  }

  @GetMapping
  public ResponseEntity<List<SelectionProcessListDto>> findAllOpenSelectionProcess() {
    List<SelectionProcessListDto> list = selectionProcessDtoAssembler.toCollectionModel(repository.findByStatusNotPending());
    return ResponseEntity.ok(list);
  }

  @Transactional
  @GetMapping("/{selectionProcessId}")
  public ResponseEntity<SelectionProcessDto> findById(@PathVariable Long selectionProcessId) {
    SelectionProcessDto selectionProcess = selectionProcessDtoAssembler.toModel(selectionProcessService.findOrFail(selectionProcessId));
    return ResponseEntity.ok(selectionProcess);
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public SelectionProcessDto create(@RequestBody @Valid InputSelectionProcess selectionProcess) {
    SelectionProcessEntity selectionProcessEntity = selectionProcessDtoDisassembler.toEntity(selectionProcess);
    return selectionProcessDtoAssembler.toModel(repository.save(selectionProcessEntity));
  }

  @PutMapping("/{selectionProcessId}/register")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public SelectionProcessDto register(@PathVariable Long selectionProcessId, Authentication authentication) {
    String email = authentication.getName();
    return selectionProcessDtoAssembler.toModel(selectionProcessService.register(selectionProcessId, email));
  }

  @PutMapping("/{selectionProcessId}/leave")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public SelectionProcessDto leave(@PathVariable Long selectionProcessId, Authentication authentication) {
    String email = authentication.getName();
    return selectionProcessDtoAssembler.toModel(selectionProcessService.leave(selectionProcessId, email));
  }

  @PatchMapping("/{selectionProcessId}/open")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void open(@PathVariable Long selectionProcessId) {
    selectionProcessFlowService.open(selectionProcessId);
  }

  @PatchMapping("/{selectionProcessId}/analysis")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void analysis(@PathVariable Long selectionProcessId) {
    selectionProcessFlowService.analysis(selectionProcessId);
  }

  @PatchMapping("/{selectionProcessId}/selection")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void selection(@PathVariable Long selectionProcessId) {
    selectionProcessFlowService.selection(selectionProcessId);
  }

  @PatchMapping("/{selectionProcessId}/finish")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void finish(@PathVariable Long selectionProcessId) {
    selectionProcessFlowService.finish(selectionProcessId);
  }
}



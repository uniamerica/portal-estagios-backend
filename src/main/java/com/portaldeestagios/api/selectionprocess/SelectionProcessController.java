package com.portaldeestagios.api.selectionprocess;

import com.portaldeestagios.api.dtos.SelectionProcessDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/selection-process")
@Api(tags = "Selection Process")
public class SelectionProcessController {

  private final SelectionProcessRepository repository;
  private final ModelMapper modelMapper;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public ResponseEntity<List<SelectionProcessDto>> findAll() {
    List<SelectionProcessDto> list = toCollectionModel(repository.findAll());
    return ResponseEntity.ok(list);
  }

  private List<SelectionProcessDto> toCollectionModel(List<SelectionProcessEntity> selectionProcessList) {
    return selectionProcessList.stream().map(this::toModel).collect(Collectors.toList());
  }
  private SelectionProcessDto toModel(SelectionProcessEntity selectionProcess){
    return modelMapper.map(selectionProcess, SelectionProcessDto.class);
  }
}



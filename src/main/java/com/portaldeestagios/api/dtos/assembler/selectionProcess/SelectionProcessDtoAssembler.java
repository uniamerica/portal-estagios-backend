package com.portaldeestagios.api.dtos.assembler.selectionProcess;

import com.portaldeestagios.api.dtos.model.selectionprocess.SelectionProcessDto;
import com.portaldeestagios.api.dtos.model.selectionprocess.SelectionProcessListDto;
import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SelectionProcessDtoAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public SelectionProcessDto toModel(SelectionProcessEntity selectionProcess) {
    return modelMapper.map(selectionProcess, SelectionProcessDto.class);
  }

  public SelectionProcessListDto toModelList(SelectionProcessEntity selectionProcess) {
    return modelMapper.map(selectionProcess, SelectionProcessListDto.class);
  }

  public List<SelectionProcessListDto> toCollectionModel(List<SelectionProcessEntity> selectionProcesses){
    return selectionProcesses.stream()
            .map(this::toModelList)
            .collect(Collectors.toList());
  }
}

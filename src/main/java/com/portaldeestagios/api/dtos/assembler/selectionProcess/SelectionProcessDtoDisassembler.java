package com.portaldeestagios.api.dtos.assembler.selectionProcess;

import com.portaldeestagios.api.dtos.inputDto.selectionprocess.InputSelectionProcess;
import com.portaldeestagios.api.selectionprocess.SelectionProcessEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectionProcessDtoDisassembler {

  @Autowired
  private ModelMapper modelMapper;

  public SelectionProcessEntity toEntity(InputSelectionProcess selectionProcess) {
    return modelMapper.map(selectionProcess, SelectionProcessEntity.class);
  }
}

package com.portaldeestagios.api.dtos.inputDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputSelectionProcess {

  @ApiModelProperty(example = "Processo seletivo Engenharia de Software da Uniamérica", required = true)
  private String title;
}

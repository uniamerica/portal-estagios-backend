package com.portaldeestagios.api.dtos.inputdto.selectionprocess;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InputSelectionProcess {
  @NotBlank
  private String companyName;

  @ApiModelProperty(example = "Processo seletivo Engenharia de Software da Uniamérica", required = true)
  @NotBlank
  private String title;
}

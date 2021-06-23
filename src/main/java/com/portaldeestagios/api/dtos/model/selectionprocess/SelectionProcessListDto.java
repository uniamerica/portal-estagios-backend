package com.portaldeestagios.api.dtos.model.selectionprocess;

import com.portaldeestagios.api.selectionprocess.SelectionProcessStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SelectionProcessListDto implements Serializable {
  private Long id;
  private String title;
  private SelectionProcessStatusEnum status;

}

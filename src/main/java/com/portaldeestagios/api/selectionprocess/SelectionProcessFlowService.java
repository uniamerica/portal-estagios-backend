package com.portaldeestagios.api.selectionprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SelectionProcessFlowService {

  @Autowired
  private SelectionProcessService selectionProcessService;

  @Transactional
  public void open(Long selectionProcessId) {
    SelectionProcessEntity selectionProcess = selectionProcessService.findOrFail(selectionProcessId);
    selectionProcess.open();
  }

  @Transactional
  public void analysis(Long selectionProcessId) {
    SelectionProcessEntity selectionProcess = selectionProcessService.findOrFail(selectionProcessId);
    selectionProcess.analysis();
  }

  @Transactional
  public void selection(Long selectionProcessId) {
    SelectionProcessEntity selectionProcess = selectionProcessService.findOrFail(selectionProcessId);
    selectionProcess.selection();
  }

  @Transactional
  public void finish(Long selectionProcessId) {
    SelectionProcessEntity selectionProcess = selectionProcessService.findOrFail(selectionProcessId);
    selectionProcess.finish();
  }
}

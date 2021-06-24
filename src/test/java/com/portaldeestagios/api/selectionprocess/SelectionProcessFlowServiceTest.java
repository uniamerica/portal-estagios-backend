package com.portaldeestagios.api.selectionprocess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.portaldeestagios.api.exception.NegocioException;
import com.portaldeestagios.api.student.Student;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SelectionProcessFlowService.class, SelectionProcessService.class})
@ExtendWith(SpringExtension.class)
public class SelectionProcessFlowServiceTest {
  @Autowired
  private SelectionProcessFlowService selectionProcessFlowService;

  @MockBean
  private SelectionProcessService selectionProcessService;

  @Test
  public void testSelectionProcessOpenWillSucceed_WhenStatusOfSelectionProcessIsPendente() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.PENDENTE, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    selectionProcessFlowService.open(123L);
    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessOpenWillFail_WhenStatusOfSelectionProcessOtherThanPendente() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.ABERTO, new HashSet<Student>());

    given(selectionProcessService.findOrFail((Long) any())).willReturn(selectionProcessEntity);
    assertThatThrownBy(() -> selectionProcessFlowService.open(123L))
            .isInstanceOf(NegocioException.class);
    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessAnalysisWillFail_WhenStatusOfSelectionProcessOtherThanAberto() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.PENDENTE, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    assertThatThrownBy(() -> selectionProcessFlowService.analysis(123L))
            .isInstanceOf(NegocioException.class);
    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessAnalysisWillSucceed_WhenStatusOfSelectionProcessIsAberto() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.ABERTO, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    selectionProcessFlowService.analysis(123L);
    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessSelectionWillFail_WhenStatusOfSelectionProcessOtherThanAnalise() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.PENDENTE, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    assertThatThrownBy(() -> selectionProcessFlowService.selection(123L))
            .isInstanceOf(NegocioException.class);
    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessSelectionWillSucceed_WhenStatusOfSelectionProcessIsAnalise() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.ANALISE, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    selectionProcessFlowService.selection(123L);
    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessFinishWillFail_WhenStatusOfSelectionProcessOtherThanSelecao() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.PENDENTE, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    assertThatThrownBy(() -> selectionProcessFlowService.finish(123L))
            .isInstanceOf(NegocioException.class);

    verify(selectionProcessService).findOrFail((Long) any());
  }

  @Test
  public void testSelectionProcessFinishWillSucceed_WhenStatusOfSelectionProcessIsSelecao() {
    SelectionProcessEntity selectionProcessEntity = new SelectionProcessEntity(123L, "Dr",
            SelectionProcessStatusEnum.SELECAO, new HashSet<Student>());

    given(selectionProcessService.findOrFail(any())).willReturn(selectionProcessEntity);
    selectionProcessFlowService.finish(123L);
    verify(selectionProcessService).findOrFail((Long) any());
  }
}


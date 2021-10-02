package com.portaldeestagios.api.selectionprocess.unit;
import com.portaldeestagios.api.selectionprocess.SelectionProcessStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionProcessStatusEnumTest {
  @Test
  void naoPodeAlterarParaAbertoQuandoStatusDiferenteDePendente() {
    Assertions.assertTrue(SelectionProcessStatusEnum.SELECAO.naoPodeAlterarPara(SelectionProcessStatusEnum.ABERTO));
    assertTrue(SelectionProcessStatusEnum.ANALISE.naoPodeAlterarPara(SelectionProcessStatusEnum.ABERTO));
    assertTrue(SelectionProcessStatusEnum.FINALIZADO.naoPodeAlterarPara(SelectionProcessStatusEnum.ABERTO));
    assertTrue(SelectionProcessStatusEnum.ABERTO.naoPodeAlterarPara(SelectionProcessStatusEnum.ABERTO));
  }

  @Test
  void deveAlterarParaAbertoQuandoStatusPendente() {
    assertFalse(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.ABERTO));
  }

  @Test
  void naoPodeAlterarParaAnaliseQuandoStatusDiferenteDeAberto() {
    assertTrue(SelectionProcessStatusEnum.SELECAO.naoPodeAlterarPara(SelectionProcessStatusEnum.ANALISE));
    assertTrue(SelectionProcessStatusEnum.ANALISE.naoPodeAlterarPara(SelectionProcessStatusEnum.ANALISE));
    assertTrue(SelectionProcessStatusEnum.FINALIZADO.naoPodeAlterarPara(SelectionProcessStatusEnum.ANALISE));
    assertTrue(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.ANALISE));
  }

  @Test
  void deveAlterarParaAnaliseQuandoStatusAberto() {
    assertFalse(SelectionProcessStatusEnum.ABERTO.naoPodeAlterarPara(SelectionProcessStatusEnum.ANALISE));
  }

  @Test
  void naoPodeAlterarParaSelecaoQuandoStatusDiferenteDeAnalise() {
    assertTrue(SelectionProcessStatusEnum.SELECAO.naoPodeAlterarPara(SelectionProcessStatusEnum.SELECAO));
    assertTrue(SelectionProcessStatusEnum.ABERTO.naoPodeAlterarPara(SelectionProcessStatusEnum.SELECAO));
    assertTrue(SelectionProcessStatusEnum.FINALIZADO.naoPodeAlterarPara(SelectionProcessStatusEnum.SELECAO));
    assertTrue(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.SELECAO));
  }

  @Test
  void deveAlterarParaSelecaoQuandoStatusAnalise() {
    assertFalse(SelectionProcessStatusEnum.ANALISE.naoPodeAlterarPara(SelectionProcessStatusEnum.SELECAO));
  }

  @Test
  void naoPodeAlterarParaFinalizadoQuandoStatusDiferenteDeSelecao() {
    assertTrue(SelectionProcessStatusEnum.ANALISE.naoPodeAlterarPara(SelectionProcessStatusEnum.FINALIZADO));
    assertTrue(SelectionProcessStatusEnum.ABERTO.naoPodeAlterarPara(SelectionProcessStatusEnum.FINALIZADO));
    assertTrue(SelectionProcessStatusEnum.FINALIZADO.naoPodeAlterarPara(SelectionProcessStatusEnum.FINALIZADO));
    assertTrue(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.FINALIZADO));
  }

  @Test
  void deveAlterarParaFinalizadoQuandoStatusSelecao() {
    assertFalse(SelectionProcessStatusEnum.SELECAO.naoPodeAlterarPara(SelectionProcessStatusEnum.FINALIZADO));
  }

  @Test
  void statusNuncaPodeAlterarParaPendente() {
    assertTrue(SelectionProcessStatusEnum.ANALISE.naoPodeAlterarPara(SelectionProcessStatusEnum.PENDENTE));
    assertTrue(SelectionProcessStatusEnum.ABERTO.naoPodeAlterarPara(SelectionProcessStatusEnum.PENDENTE));
    assertTrue(SelectionProcessStatusEnum.FINALIZADO.naoPodeAlterarPara(SelectionProcessStatusEnum.PENDENTE));
    assertTrue(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.PENDENTE));
    assertTrue(SelectionProcessStatusEnum.SELECAO.naoPodeAlterarPara(SelectionProcessStatusEnum.PENDENTE));
  }
}


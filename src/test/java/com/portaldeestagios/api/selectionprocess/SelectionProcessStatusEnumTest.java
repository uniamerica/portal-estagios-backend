package com.portaldeestagios.api.selectionprocess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SelectionProcessStatusEnumTest {
  @Test
  public void testNaoPodeAlterarPara() {
    assertTrue(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.PENDENTE));
    assertFalse(SelectionProcessStatusEnum.PENDENTE.naoPodeAlterarPara(SelectionProcessStatusEnum.ABERTO));
  }
}


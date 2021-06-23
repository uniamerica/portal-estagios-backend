package com.portaldeestagios.api.selectionprocess;

import java.util.Arrays;
import java.util.List;

public enum SelectionProcessStatusEnum {
  PENDENTE("Pendente"),
  ABERTO("Aberto", PENDENTE),
  ANALISE("Em análise", ABERTO),
  SELECAO("Seleção", ANALISE),
  FINALIZADO("Finalizado", SELECAO);

  private final String description;
  private final List<SelectionProcessStatusEnum> previousStatus;

  SelectionProcessStatusEnum(String description, SelectionProcessStatusEnum... previousStatus) {
    this.description = description;
    this.previousStatus = Arrays.asList(previousStatus);
  }

  public String getDescription() {
    return this.description;
  }

  public boolean naoPodeAlterarPara(SelectionProcessStatusEnum novoStatus) {
    return !novoStatus.previousStatus.contains(this);
  }
}

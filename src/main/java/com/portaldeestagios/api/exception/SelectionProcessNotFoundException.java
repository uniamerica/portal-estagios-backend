package com.portaldeestagios.api.exception;

public class SelectionProcessNotFoundException extends ResourceNotFoundException {
  public static final long serialVersionUID = 1L;

  public SelectionProcessNotFoundException(String mensagem) {
    super(mensagem);
  }

  public SelectionProcessNotFoundException(Long selectionProcessId) {
    this("Não existe um cadastro de processo seletivo com código " + selectionProcessId);
  }
}

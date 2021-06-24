package com.portaldeestagios.api.exception;

public abstract class ResourceNotFoundException extends NegocioException {
  public static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String mensagem) {
    super(mensagem);
  }
}

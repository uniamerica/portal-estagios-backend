package com.portaldeestagios.api.exception;

public class UserNotFoundException extends ResourceNotFoundException {
  public static final long serialVersionUID = 1L;

  public UserNotFoundException(String mensagem) {
    super(mensagem);
  }

  public UserNotFoundException(Long userId) {
    this("Não existe um usuário com código " + userId);
  }
}

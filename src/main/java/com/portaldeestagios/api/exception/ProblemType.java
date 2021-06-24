package com.portaldeestagios.api.exception;

import lombok.Getter;

@Getter
public enum ProblemType {
  DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
  ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
  CONFLITO("/conflito", "Já um existe um cadastro");

  private final String title;
  private final String uri;

  ProblemType(String path, String title) {
    this.uri = "https://portaldeestagios.org.br" + path;
    this.title = title;
  }
}

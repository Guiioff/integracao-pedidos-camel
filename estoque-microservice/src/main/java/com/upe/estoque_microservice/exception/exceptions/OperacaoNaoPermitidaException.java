package com.upe.estoque_microservice.exception.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
  public OperacaoNaoPermitidaException(String descricao) {
    super(descricao);
  }
}

package com.upe.estoque_microservice.exception.exceptions;

public class NaoEncontradoException extends RuntimeException {
  public NaoEncontradoException(String descricao) {
    super(descricao);
  }
}

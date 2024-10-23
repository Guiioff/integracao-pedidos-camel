package com.upe.estoque_microservice.exception;

import com.upe.estoque_microservice.exception.exceptions.NaoEncontradoException;
import com.upe.estoque_microservice.exception.exceptions.OperacaoNaoPermitidaException;
import com.upe.estoque_microservice.exception.message.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

  private static final String NAO_ENCONTRADO_ERRO_TITULO = "Elemento não encontrado";
  private static final String OPERACAO_NAO_PERMITIDA_ERRO_TITULO =
      "Operação não permitida ou não autorizada";

  @ExceptionHandler(NaoEncontradoException.class)
  public ResponseEntity<ErroDto> handleNaoEncontradoException(NaoEncontradoException ex) {
    ErroDto erro =
        new ErroDto(HttpStatus.NOT_FOUND.value(), NAO_ENCONTRADO_ERRO_TITULO, ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
  }

  @ExceptionHandler(OperacaoNaoPermitidaException.class)
  public ResponseEntity<ErroDto> handleOperacaoNaoPermitidaException(
      OperacaoNaoPermitidaException ex) {
    ErroDto erro =
        new ErroDto(
            HttpStatus.CONFLICT.value(), OPERACAO_NAO_PERMITIDA_ERRO_TITULO, ex.getMessage());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
  }
}

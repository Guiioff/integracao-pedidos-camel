package com.upe.pagamento_microservice.controller;

import com.upe.pagamento_microservice.controller.dto.PagamentoRequest;
import com.upe.pagamento_microservice.controller.dto.PagamentoResponse;
import com.upe.pagamento_microservice.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PagamentoController {

  private final PagamentoService service;

  @PostMapping("/pagamentos")
  public ResponseEntity<PagamentoResponse> validarPagamento(@RequestBody PagamentoRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.validarPagamento(request));
  }

  @GetMapping("/pagamentos")
  public String teste() {
    return "teste";
  }
}

package com.upe.pagamento_microservice.service;

import com.upe.pagamento_microservice.controller.dto.PagamentoRequest;
import com.upe.pagamento_microservice.controller.dto.PagamentoResponse;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PagamentoService {

  public PagamentoResponse validarPagamento(PagamentoRequest request) {
    return new PagamentoResponse(request.produtoId(), obterStatusAleatorio());
  }

  private String obterStatusAleatorio() {
    String[] statusArr = {"APROVADO", "REJEITADO", "ERRO"};
    return statusArr[new Random().nextInt(statusArr.length)];
  }
}

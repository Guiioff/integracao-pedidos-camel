package com.upe.pagamento_microservice.controller.dto;

import java.math.BigDecimal;

public record PagamentoRequest(
    Long id, Long produtoId, int quantidade, String nomeCliente, BigDecimal valorTotal,
    String status) {

}

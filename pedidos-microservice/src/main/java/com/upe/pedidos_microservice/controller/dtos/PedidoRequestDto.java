package com.upe.pedidos_microservice.controller.dtos;

import java.math.BigDecimal;

public record PedidoRequestDto(Long produtoId, int quantidade, String nomeCliente, BigDecimal valorTotal) {
}

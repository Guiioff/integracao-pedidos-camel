package com.upe.pedidos_microservice.controller.dtos;

import lombok.Data;

@Data
public record PedidoRequestDto(Long produtoId, int quantidade, String nomeCliente) {
}

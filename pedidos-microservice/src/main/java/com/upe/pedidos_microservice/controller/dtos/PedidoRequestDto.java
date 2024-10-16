package com.upe.pedidos_microservice.controller.dtos;

public record PedidoRequestDto(Long produtoId, int quantidade, String nomeCliente) {
}

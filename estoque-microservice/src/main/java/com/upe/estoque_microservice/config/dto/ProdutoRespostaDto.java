package com.upe.estoque_microservice.config.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoRespostaDto(
    Long id, String nome, BigDecimal preco, Long quantidadeDisponivel) {}

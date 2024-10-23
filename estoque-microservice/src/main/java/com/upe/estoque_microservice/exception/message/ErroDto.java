package com.upe.estoque_microservice.exception.message;

public record ErroDto(Integer status, String titulo, String descricao) {}

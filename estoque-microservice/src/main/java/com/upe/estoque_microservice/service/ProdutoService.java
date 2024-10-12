package com.upe.estoque_microservice.service;

import com.upe.estoque_microservice.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {
  private final ProdutoRepository produtoRepository;
}

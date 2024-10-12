package com.upe.estoque_microservice.controller;

import com.upe.estoque_microservice.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {
  private final ProdutoService produtoService;
}

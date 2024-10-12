package com.upe.estoque_microservice.controller;

import com.upe.estoque_microservice.controller.dto.ProdutoRespostaDto;
import com.upe.estoque_microservice.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {
  private final ProdutoService produtoService;

  @GetMapping
  public ResponseEntity<Page<ProdutoRespostaDto>> listarProdutos(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "10") int tamanho) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.produtoService.listarProdutos(pagina, tamanho));
  }
}

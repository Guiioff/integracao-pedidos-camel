package com.upe.estoque_microservice.service;

import com.upe.estoque_microservice.config.dto.ProdutoRespostaDto;
import com.upe.estoque_microservice.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {
  private final ProdutoRepository produtoRepository;

  public Page<ProdutoRespostaDto> listarProdutos(int pagina, int tamanho) {
    Pageable pageable = PageRequest.of(pagina, tamanho);
    return this.produtoRepository
        .findAll(pageable)
        .map(
            produto ->
                ProdutoRespostaDto.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .preco(produto.getPreco())
                    .quantidadeDisponivel(produto.getQuantidadeDisponivel())
                    .build());
  }
}

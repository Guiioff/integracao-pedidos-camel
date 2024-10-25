package com.upe.estoque_microservice.service;

import com.upe.estoque_microservice.controller.dto.ProdutoRespostaDto;
import com.upe.estoque_microservice.exception.exceptions.NaoEncontradoException;
import com.upe.estoque_microservice.exception.exceptions.OperacaoNaoPermitidaException;
import com.upe.estoque_microservice.model.Produto;
import com.upe.estoque_microservice.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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

  @Transactional
  public void alterarQuantidadeProduto(Long id, Long quantidade) {
    Produto produto =
        this.produtoRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new NaoEncontradoException("Nenhum produto foi encontrado com o ID fornecido"));

    if (produto.getQuantidadeDisponivel() < quantidade) {
      throw new OperacaoNaoPermitidaException(
          "A quantidade em estoque é menor que a quantidade solicitada");
    }

    produto.setQuantidadeDisponivel(produto.getQuantidadeDisponivel() - quantidade);
    this.produtoRepository.save(produto);
    log.info("Removida(s) " + quantidade + " unidade(s) de " + produto.getNome() + " do estoque.");
  }
}

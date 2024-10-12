package com.upe.estoque_microservice.config;

import com.upe.estoque_microservice.model.Produto;
import com.upe.estoque_microservice.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopularBancoConfig implements CommandLineRunner {
  private final ProdutoRepository produtoRepository;

  @Override
  public void run(String... args) {
    if (this.produtoRepository.count() == 0) {
      Produto produto1 =
          Produto.builder()
              .nome("Smartphone Samsung Galaxy S24 Ultra")
              .preco(BigDecimal.valueOf(8999.99))
              .quantidadeDisponivel(30L)
              .build();

      Produto produto2 =
          Produto.builder()
              .nome("Ar Condicionado Split LG 9000 BTUs")
              .preco(BigDecimal.valueOf(2500.00))
              .quantidadeDisponivel(15L)
              .build();

      Produto produto3 =
          Produto.builder()
              .nome("Placa de Vídeo RTX 4060 8GB")
              .preco(BigDecimal.valueOf(2230.99))
              .quantidadeDisponivel(75L)
              .build();

      this.produtoRepository.saveAll(List.of(produto1, produto2, produto3));
    }
  }
}

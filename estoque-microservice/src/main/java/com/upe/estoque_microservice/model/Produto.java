package com.upe.estoque_microservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto")
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nome;

  @Column(nullable = false)
  private BigDecimal preco;

  @Column(nullable = false)
  private Long quantidadeDisponivel;
}

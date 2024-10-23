package com.upe.estoque_microservice.listener;

import com.upe.estoque_microservice.config.RabbitMQConfig;
import com.upe.estoque_microservice.listener.message.AlterarEstoqueMessage;
import com.upe.estoque_microservice.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EstoqueListener {
  private final ProdutoService produtoService;

  @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void listenAlterarEstoqueQueue(AlterarEstoqueMessage message) {
    try {
      this.produtoService.alterarQuantidadeProduto(message.id(), message.quantidade());
    } catch (Exception ex) {
      log.error("Erro na operação: " + ex.getMessage());
    }
  }
}

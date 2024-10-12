package com.upe.estoque_microservice.listener;

import com.upe.estoque_microservice.config.RabbitMQConfig;
import com.upe.estoque_microservice.listener.message.AlterarEstoqueMessage;
import com.upe.estoque_microservice.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EstoqueListener {
  private final ProdutoService produtoService;

  @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void listenAlterarEstoqueQueue(AlterarEstoqueMessage message) {
    this.produtoService.alterarQuantidadeProduto(message.id(), message.quantidade());
  }
}

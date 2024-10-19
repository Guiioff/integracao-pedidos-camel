package com.upe.pedidos_microservice.camel.route;

import com.upe.pedidos_microservice.camel.processor.MessageProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PedidoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:pedidos-queue")
                .log("Recebido pedido na fila: ${body}")
                .process(new MessageProcessor())
                .log("Mensagem processada: ${body}")
                .to("spring-rabbitmq:estoque-exchange?routingKey=alterar-estoque");

    }
}